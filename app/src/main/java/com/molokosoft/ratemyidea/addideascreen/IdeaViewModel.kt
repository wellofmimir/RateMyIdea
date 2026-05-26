package com.molokosoft.ratemyidea.addideascreen

import androidx.lifecycle.ViewModel
import com.molokosoft.ratemyidea.repositories.IdeaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import com.molokosoft.ratemyidea.network.model.Idea
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.app.Activity
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.molokosoft.ratemyidea.billing.CreditCatalog

import kotlinx.coroutines.delay
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class IdeaViewModel (
    private val ideaRepository: IdeaRepository
): ViewModel() {

    private val internToastEvent = MutableSharedFlow<String>()
    val toastEvent = internToastEvent.asSharedFlow()

    private val internInsertFirstIdeaEvent = MutableSharedFlow<Unit>()
    val insertFirstIdeaEvent = internInsertFirstIdeaEvent.asSharedFlow()

    val internFirstIdeaInsertedEvent = MutableSharedFlow<Unit>()
    val firstIdeaInsertedEvent = internFirstIdeaInsertedEvent.asSharedFlow()
    var firstIdeaInserted = false

    val internIdeaInserted = MutableSharedFlow<Boolean>()
    val ideaInserted = internIdeaInserted.asSharedFlow()

    var internAgeBracketNeeded = MutableSharedFlow<Boolean>()
    val ageBracketNeeded = internAgeBracketNeeded.asSharedFlow()

    var internGenderNeeded = MutableSharedFlow<Boolean>()
    val genderNeeded = internGenderNeeded.asSharedFlow()
    private val internUserIdeas = MutableStateFlow<List<Idea>>(emptyList())
    val userIdeas = internUserIdeas.asStateFlow()

    private var internIdea = MutableStateFlow<Idea?>(null)
    val idea = internIdea.asStateFlow()

    var internPostingUnlocked = MutableSharedFlow<Boolean>()
    val postingUnlocked = internPostingUnlocked.asSharedFlow()


    var titleText by mutableStateOf("")
        private set

    fun updateTitleText(text: String) {
        titleText = text
    }

    var descriptionText by mutableStateOf("")
        private set

    fun updateDescriptionText(text: String) {
        descriptionText = text
    }

    fun clearTitleAndDescription() {
        titleText = ""
        descriptionText = ""
    }

    fun rateIdea (
        like: Boolean
    ) {
        if (internIdea.value == null || internIdea.value!!.uuid.isBlank())
            return

        viewModelScope.launch {
            ideaRepository.rateIdea(internIdea.value!!, like)
            ideaRepository.incrementIdeasRated()

            if (!ideaRepository.firstIdeaRated())
                ideaRepository.setFirstIdeaRated()

            internIdea.value?.let { idea ->
                val percentageOfLikes = if (idea.total > 0) {
                    idea.likes.toFloat() / idea.total.toFloat() * 100
                } else {
                    0f
                }

                if (ideaRepository.ideasRated() == ideaRepository.ideasToRate() - (ideaRepository.ideasToRate() / 2)) {
                    internToastEvent.emit("Only ${ideaRepository.ideasToRate() / 2} ideas left to rate and you can post a own new idea!")
                }

                if (ideaRepository.ideasRated() <= ideaRepository.ideasToRate() && ideaRepository.ideasRated() % 5 == 0) {
                    if (percentageOfLikes == 0.0f)
                        internToastEvent.emit("You are the first to rate this idea!")
                    else
                        internToastEvent.emit("${percentageOfLikes.toInt()}% of users liked this idea!")

                } else if (ideaRepository.ideasRated() > ideaRepository.ideasToRate() && ideaRepository.ideasRated() % 10 == 0) {
                    if (percentageOfLikes == 0.0f)
                        internToastEvent.emit("You are the first to rate this idea!")
                    else
                        internToastEvent.emit("${percentageOfLikes.toInt()}% of users liked this idea!")
                }
            }

            internIdea.value = null
            delay(1250)
            fetchIdea()
        }

        viewModelScope.launch {
            ideaRepository.rewardEarned.collect {
                internPostingUnlocked.emit(true)
            }
        }
    }

    fun fetchIdeasForUser () {
        viewModelScope.launch {
            val ideas = ideaRepository.fetchIdeasForUser()
            internUserIdeas.value = ideas
        }
    }

    suspend fun fetchIdea() {
        val idea = ideaRepository.fetchIdea()

        if (idea == null) {
            internIdea.value = Idea("Done", "You rated all ideas!" ,"Post your own idea and let it get rated!")
            return
        }

        internIdea.value = idea

        if (ideaRepository.ideasRated() == 1 && !ideaRepository.firstIdeaRated()) {
            internToastEvent.emit("Let's get started!")

        } else if (ideaRepository.ideasRated() == 5 && !ideaRepository.firstSwitchToInsertIdeaDone() && !ideaRepository.firstIdeaInserted()) {
            internToastEvent.emit("Now get your own idea into the mix!")
            internInsertFirstIdeaEvent.emit(Unit)
            ideaRepository.setFirstSwitchToInsertIdeaDone()
        }
    }

    fun updateIdea (
        idea: Idea
    ) {
        viewModelScope.launch {
            if (!firstIdeaInserted && !ideaRepository.firstIdeaInserted()) {
                firstIdeaInserted = true
                internFirstIdeaInsertedEvent.emit(Unit)
                internToastEvent.emit("Here you can see how your idea is rated.")
                ideaRepository.setFirstIdeaInserted()
            } else {
                internToastEvent.emit("Your idea is out there.")
                internToastEvent.emit("Rate ${ideaRepository.ideasToRate()} ideas to post your next idea for free!")
            }

            internIdeaInserted.emit(true)
            ideaRepository.updateIdea(idea)
            fetchIdeasForUser()

            if (ideaRepository.rateMyIdeaRewardEarned())
                ideaRepository.resetRateMyIdeaRewardEarned()
        }
    }

    fun checkPostingUnlocked(activity: Activity) {
        if (!ideaRepository.firstIdeaInserted()) {
            if (ideaRepository.ageBracket().isBlank()) {
                viewModelScope.launch {
                    internAgeBracketNeeded.emit(true)
                }
            } else if (ideaRepository.gender().isBlank()) {
                viewModelScope.launch {
                    internGenderNeeded.emit(true)
                }
            } else {
                viewModelScope.launch {
                    internPostingUnlocked.emit(true)
                }
            }
        } else if (ideaRepository.ideasRated() >= ideaRepository.ideasToRate()) {
            if (ideaRepository.rateMyIdeaRewardEarned()) {
                viewModelScope.launch {
                    internPostingUnlocked.emit(true)
                }
            } else {
                ideaRepository.onWatchAd (
                    activity,
                    onReward = {
                        viewModelScope.launch {
                            internPostingUnlocked.emit(true)
                        }
                    },
                    onClosed = {
                    }
                )
            }

        } else if (ideaRepository.rateMyIdeaCredits() > 0) {
            viewModelScope.launch {
                internPostingUnlocked.emit(true)
            }
        } else {
            viewModelScope.launch {
                internPostingUnlocked.emit(false)
            }
        }
    }

    fun purchaseRateMyIdeaCredits(activity: Activity) {
        ideaRepository.purchaseRateMyIdeaCredits(activity)
    }

    fun ideasToRate(): Int {
        return ideaRepository.ideasToRate()
    }

    fun ideasRated(): Int {
        return ideaRepository.ideasRated()
    }

    fun setAgeBracket(ageBracket: String) {
        ideaRepository.setAgeBracket(ageBracket)
    }

    fun setGender(gender: String) {
        ideaRepository.setGender(gender)
    }

    fun purchaseDeeperInsights(activity: Activity) {
        ideaRepository.purchaseDeeperInsights(activity)
    }

    fun deeperInsightsUnlocked(): Boolean {
        return ideaRepository.deeperInsightsUnlocked()
    }

    fun alreadyInsertedAnIdea(): Boolean {
        return ideaRepository.firstIdeaInserted()
    }
}