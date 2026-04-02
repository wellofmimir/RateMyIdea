package com.example.ratemyidea.addideascreen

import androidx.lifecycle.ViewModel
import com.example.ratemyidea.repositories.IdeaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.ratemyidea.network.model.Idea
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.app.Activity

class IdeaViewModel (
    private val ideaRepository: IdeaRepository
): ViewModel() {

    private val internToastEvent = MutableSharedFlow<String>()
    val toastEvent = internToastEvent.asSharedFlow()

    private val internInsertFirstIdeaEvent = MutableSharedFlow<Boolean>()
    val insertFirstIdeaEvent = internInsertFirstIdeaEvent.asSharedFlow()

    val internFirstIdeaInsertedEvent = MutableSharedFlow<Boolean>()
    val firstIdeaInsertedEvent = internFirstIdeaInsertedEvent.asSharedFlow()
    var firstIdeaInserted = false

    private val internUserIdeas = MutableStateFlow<List<Idea>>(emptyList())
    val userIdeas = internUserIdeas.asStateFlow()

    private var internIdea = MutableStateFlow<Idea?>(null)
    val idea = internIdea.asStateFlow()

    init {
        viewModelScope.launch {
            fetchIdea()
        }
    }

    fun rateIdea (
        like: Boolean
    ) {
        if (internIdea.value == null || internIdea.value!!.uuid.isBlank())
            return

        viewModelScope.launch {
            ideaRepository.rateIdea(internIdea.value!!, like)
            ideaRepository.incrementIdeasRated()
            fetchIdea()
        }

    }

    suspend fun fetchIdeasForUser () {
        viewModelScope.launch {
            val ideas = ideaRepository.fetchIdeasForUser()
            internUserIdeas.value = ideas
        }
    }

    suspend fun fetchIdea() {
        val idea = ideaRepository.fetchIdea()

        if (idea == null) {
            internToastEvent.emit("You rated all ideas!")
            internIdea.value = Idea("", "You're all done!", "Publish your own idea and let it get rated!")
            return
        }

        internIdea.value = idea

        val percentageOfLikes = if (idea.total > 0) {
            idea.likes.toFloat() / idea.total.toFloat() * 100
        } else {
            0f
        }

        if (ideaRepository.ideasRated() == 1)
            internToastEvent.emit( if (percentageOfLikes == 0f) "Nice." else "${percentageOfLikes} liked this idea!")
        else if (ideaRepository.ideasRated() == 5) {
            //internInsertFirstIdeaEvent.emit(true)
            internToastEvent.emit("Get your own idea into the mix!")
        }
    }

    fun updateIdea (
        idea: Idea
    ) {
        viewModelScope.launch {
            ideaRepository.updateIdea(idea)
            fetchIdeasForUser()

            if (!firstIdeaInserted && !ideaRepository.firstIdeaInserted()) {
                firstIdeaInserted = true
                internFirstIdeaInsertedEvent.emit(true)
                internToastEvent.emit("Here you can see how your idea is rated.")
                ideaRepository.setFirstIdeaInserted()
            } else {
                internToastEvent.emit("Your idea is out there.")
            }
        }
    }

    fun ideasRated(): Int {
        return ideaRepository.ideasRated()
    }

    fun onWatchAd (
        activity: Activity
    ) {
        ideaRepository.onWatchAd(activity)
    }
}