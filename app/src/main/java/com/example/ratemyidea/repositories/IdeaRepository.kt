package com.example.ratemyidea.repositories

import android.app.Activity
import com.example.ratemyidea.advertisement.RewardedAdManager
import com.example.ratemyidea.database.Database
import com.example.ratemyidea.network.IdeaClient
import com.example.ratemyidea.network.model.Idea
import com.example.ratemyidea.securepreferences.SecurePreferences
import java.util.UUID

class IdeaRepository (
    private val ideaClient: IdeaClient,
    private val database: Database,
    private val rewardedAdManager: RewardedAdManager,
    private val securePreferences: SecurePreferences
) {
    init {
        //es wird eine einmalige uuid erstellt,
        //die in den Secure-Prefs gespeichert wird
        //und die den user auf dem Server identifiziert
        //Quasi der User Account

        if (securePreferences.uuid().isBlank()) {
            val uuid = UUID.randomUUID().toString()
            securePreferences.setUUID(uuid)
        }
    }

    suspend fun fetchIdeasForUser (): List<Idea> {
        val userUuid = securePreferences.uuid()
        val ideas = ideaClient.fetchIdeasForUser(userUuid)
        return ideas
    }

    suspend fun fetchIdea(): Idea? {
        val userUuid = securePreferences.uuid()
        val idea = ideaClient.fetchIdea(userUuid)
        return idea
    }

    suspend fun rateIdea (
        idea: Idea,
        like: Boolean
    ) {
        val userUuid = securePreferences.uuid()
        ideaClient.rateIdea(userUuid, idea, like)
        database.insertAlreadyRatedIdea(idea.uuid)
    }

    suspend fun updateIdea (
        idea: Idea
    ): Idea {
        idea.userUuid = securePreferences.uuid()
        val updatedIdea = ideaClient.updateIdea(idea)
        database.insertIdea(updatedIdea)
        return updatedIdea
    }

    fun incrementIdeasRated() {
        securePreferences.incrementIdeasRated()
    }

    fun ideasRated(): Int {
        return securePreferences.ideasRated()
    }

    fun setFirstIdeaInserted() {
        securePreferences.setFirstIdeaInserted()
    }

    fun firstIdeaInserted(): Boolean {
        return securePreferences.firstIdeaInserted()
    }

    fun onWatchAd (
        activity: Activity
    ) {
        if (rewardedAdManager.isReady()) {
            rewardedAdManager.show (
                activity,
                onReward = {
                },
                onClosed = {
                    rewardedAdManager.load("ca-app-pub-3940256099942544/5224354917")
                }
            )
        } else {
        }
    }
}