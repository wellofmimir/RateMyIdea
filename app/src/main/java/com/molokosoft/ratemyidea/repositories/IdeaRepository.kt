package com.molokosoft.ratemyidea.repositories

import android.app.Activity
import androidx.core.content.edit
import com.molokosoft.ratemyidea.advertisement.RewardedAdManager
import com.molokosoft.ratemyidea.billing.BillingManager
import com.molokosoft.ratemyidea.billing.CreditCatalog
import com.molokosoft.ratemyidea.database.Database
import com.molokosoft.ratemyidea.network.IdeaClient
import com.molokosoft.ratemyidea.network.model.Idea
import com.molokosoft.ratemyidea.securepreferences.SecurePreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.util.UUID
import kotlinx.coroutines.flow.asSharedFlow

class IdeaRepository (
    private val ideaClient: IdeaClient,
    private val database: Database,
    private val billingManager: BillingManager,
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

        billingManager.start()
    }

    private val internRewardEarned = MutableSharedFlow<Unit>()
    val rewardEarned = internRewardEarned.asSharedFlow()

    fun rateMyIdeaRewardEarned(): Boolean {
        return securePreferences.rateMyIdeaRewardEarned()
    }

    fun resetRateMyIdeaRewardEarned() {
        securePreferences.resetRateMyIdeaRewardEarned()
    }


    suspend fun fetchIdeasForUser (): List<Idea> {
        val userUuid = securePreferences.uuid()
        val ideas = ideaClient.fetchIdeasForUser(userUuid)
        return ideas
    }

    suspend fun fetchIdea(): Idea? {
        val userUuid = securePreferences.uuid()

        val idea = if (securePreferences.currentIdea() == null || securePreferences.currentIdea()!!.uuid == "Done") {
            val newIdea = ideaClient.fetchIdea(userUuid)
            securePreferences.setCurrentIdea(newIdea)
            newIdea
        }
        else
            securePreferences.currentIdea()

        return idea
    }

    suspend fun rateIdea (
        idea: Idea,
        like: Boolean
    ) {
        val userUuid = securePreferences.uuid()
        ideaClient.rateIdea(userUuid, gender(), ageBracket(), idea, like)
        securePreferences.resetCurrentIdea()
    }

    suspend fun updateIdea (
        idea: Idea
    ): Idea {
        idea.userUuid = securePreferences.uuid()
        val updatedIdea = ideaClient.updateIdea(idea)
        database.insertIdea(updatedIdea)
        securePreferences.decrementRateMyIdeaCredits()
        return updatedIdea
    }

    fun setFirstSwitchToInsertIdeaDone() {
        securePreferences.setFirstSwitchToInsertIdeaDone()
    }

    fun firstSwitchToInsertIdeaDone(): Boolean {
        return securePreferences.firstSwitchToInsertIdeaDone()
    }

    fun incrementIdeasRated() {
        securePreferences.incrementIdeasRated()
    }

    fun ideasRated(): Int {
        return securePreferences.ideasRated()
    }

    fun ideasToRate(): Int {
        return securePreferences.ideasToRate()
    }

    fun setFirstIdeaRated() {
        securePreferences.setFirstIdeaRated()
    }

    fun firstIdeaRated(): Boolean {
        return securePreferences.firstIdeaRated()
    }

    fun setFirstIdeaInserted() {
        securePreferences.setFirstIdeaInserted()
    }

    fun firstIdeaInserted(): Boolean {
        return securePreferences.firstIdeaInserted()
    }


    fun onWatchAd (
        activity: Activity,
        onReward: () -> Unit,
        onClosed: () -> Unit
    ) {
        if (rewardedAdManager.isReady()) {
            rewardedAdManager.show (
                activity,
                onReward = {
                    securePreferences.setRateMyIdeaRewardEarned()
                    securePreferences.resetIdeasRated()
                    onReward()
                },
                onClosed = {
                    rewardedAdManager.load("ca-app-pub-8967992746965159/4997477708")

                    if (securePreferences.rateMyIdeaRewardEarned()) {
                        securePreferences.resetRateMyIdeaRewardEarned()
                    }

                    onClosed()
                }
            )
        }
    }

    fun incrementRateMyIdeaCredits() {
        securePreferences.incrementRateMyIdeaCredits()
    }

    fun rateMyIdeaCredits(): Int {
        return securePreferences.rateMyIdeaCredits()
    }

    fun purchaseRateMyIdeaCredits(activity: Activity) {
        billingManager.clearListener()

        billingManager.setListener(object: BillingManager.Listener {
            override fun onPurchaseSuccess(idProduct: String) {
                CoroutineScope(Dispatchers.Main).launch {
                    incrementRateMyIdeaCredits()
                }
            }
        })

        billingManager.start()
        billingManager.buyProduct(activity, "posting_unlocked")
    }

    fun purchaseDeeperInsights(activity: Activity) {
        billingManager.clearListener()

        billingManager.setListener(object: BillingManager.Listener {
            override fun onPurchaseSuccess(idProduct: String) {
                CoroutineScope(Dispatchers.Main).launch {
                    CreditCatalog.deeperInsightsPurchased(true)
                    setDeeperInsightsUnlocked()
                }
            }
        })

        billingManager.start()
        billingManager.buyProduct(activity, "unlock_deeper_insights")
    }

    fun setGender(gender: String) {
        securePreferences.setGender(gender)
    }

    fun gender(): String {
        return securePreferences.gender()
    }

    fun setAgeBracket(ageBracket: String) {
        securePreferences.setAgeBracket(ageBracket)
    }

    fun ageBracket(): String {
        return securePreferences.ageBracket()
    }

    fun setDeeperInsightsUnlocked() {
        securePreferences.setDeeperInsightsUnlocked()
    }

    fun deeperInsightsUnlocked(): Boolean {
        return securePreferences.deeperInsightsUnlocked()
    }
}