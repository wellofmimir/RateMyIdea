package com.molokosoft.ratemyidea.securepreferences

import android.content.Context
import kotlin.getValue
import androidx.core.content.edit

import androidx.security.crypto.MasterKey
import androidx.security.crypto.EncryptedSharedPreferences
import com.molokosoft.ratemyidea.network.model.Idea

class SecurePreferences (
    context: Context
) {
    private val masterKey by lazy {
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
    }

    private val securePreferences by lazy {
        EncryptedSharedPreferences.create (
            context,
            "securePreferences",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun setCurrentIdea(idea: Idea) {
        securePreferences.edit {
            putString("currentIdeaUuid", idea.uuid)
            putString("currentIdeaTitle", idea.title)
            putString("currentIdeaDescription", idea.description)
            putString("currentIdeaTotal", idea.total.toString())
            putString("currentIdeaLikes", idea.likes.toString())
            putString("currentIdeaDislikes", idea.dislikes.toString())
            putString("currentIdeaUserUuid", idea.userUuid)
        }
    }

    fun resetCurrentIdea() {
        securePreferences.edit {
            putString("currentIdeaUuid", "")
            putString("currentIdeaTitle", "")
            putString("currentIdeaDescription", "")
            putString("currentIdeaTotal", "")
            putString("currentIdeaLikes", "")
            putString("currentIdeaDislikes", "")
            putString("currentIdeaUserUuid", "")
        }
    }

    fun currentIdea(): Idea? {
        if (securePreferences.getString("currentIdeaDescription", "").isNullOrBlank())
            return null

        return Idea (
            uuid = securePreferences.getString("currentIdeaUuid", "") ?: "",
            title = securePreferences.getString("currentIdeaTitle", "") ?: "",
            description = securePreferences.getString("currentIdeaDescription", "") ?: "",
            total = securePreferences.getString("currentIdeaTotal", "")?.toInt() ?: 0,
            likes = securePreferences.getString("currentIdeaLikes", "")?.toInt() ?: 0,
            dislikes = securePreferences.getString("currentIdeaDislikes", "")?.toInt() ?: 0,
            userUuid = securePreferences.getString("currentIdeaUserUuid", "") ?: ""
        )
    }

    fun setUUID(uuid: String) {
        securePreferences.edit {
            putString("uuid", uuid)
        }
    }

    fun uuid(): String {
        return securePreferences.getString("uuid", "") ?: ""
    }

    fun setFirstSwitchToInsertIdeaDone() {
        securePreferences.edit {
            putBoolean("firstSwitchToInsertIdeaDone", true)
        }
    }

    fun firstSwitchToInsertIdeaDone(): Boolean {
        return securePreferences.getBoolean("firstSwitchToInsertIdeaDone", false)
    }

    fun setFirstIdeaInserted() {
        securePreferences.edit {
            putBoolean("firstIdeaInserted", true)
        }
    }

    fun firstIdeaInserted(): Boolean {
        return securePreferences.getBoolean("firstIdeaInserted", false)
    }


    fun setFirstIdeaRated() {
        securePreferences.edit {
            putBoolean("firstIdeaRated", true)
        }
    }
    fun firstIdeaRated(): Boolean {
        return securePreferences.getBoolean("firstIdeaRated", false)
    }

    fun incrementIdeasRated() {
        val currentIdeasRated = securePreferences.getInt("ideasRated", 0)
        securePreferences.edit {
            putInt("ideasRated", currentIdeasRated + 1)
        }
    }

    fun ideasToRate(): Int {
        return 12
    }

    fun ideasRated(): Int {
        return securePreferences.getInt("ideasRated", 0)
    }

    fun resetIdeasRated() {
        securePreferences.edit {
            putInt("ideasRated", 0)
        }
    }

    fun setRateMyIdeaRewardEarned() {
        securePreferences.edit {
            putBoolean("rateMyIdeaRewardEarned", true)
        }
    }

    fun resetRateMyIdeaRewardEarned() {
        securePreferences.edit {
            putBoolean("rateMyIdeaRewardEarned", false)
        }
    }

    fun rateMyIdeaRewardEarned(): Boolean {
        return securePreferences.getBoolean("rateMyIdeaRewardEarned", false)
    }

    fun incrementRateMyIdeaCredits() {
        val rateMyIdeaCredits = securePreferences.getInt("rateMyIdeaCredits", 0)

        securePreferences.edit {
            putInt("rateMyIdeaCredits", rateMyIdeaCredits + 1)
        }
    }

    fun decrementRateMyIdeaCredits() {
        val rateMyIdeaCredits = securePreferences.getInt("rateMyIdeaCredits", 0)

        if (rateMyIdeaCredits == 0)
            return

        securePreferences.edit {
            putInt("rateMyIdeaCredits", rateMyIdeaCredits - 1)
        }
    }

    fun rateMyIdeaCredits(): Int {
        val postingCredits = securePreferences.getInt("rateMyIdeaCredits", 0)
        return postingCredits
    }

    fun setGender(gender: String) {
        securePreferences.edit {
            putString("gender", gender)
        }
    }

    fun gender(): String {
        return securePreferences.getString("gender", "") ?: ""
    }

    fun setAgeBracket(ageBracket: String) {
        securePreferences.edit {
            putString("ageBracket", ageBracket)
        }
    }

    fun ageBracket(): String {
        return securePreferences.getString("ageBracket", "") ?: ""
    }

    fun setDeeperInsightsUnlocked() {
        securePreferences.edit {
            putBoolean("deeperInsightsUnlocked", true)
        }
    }

    fun deeperInsightsUnlocked(): Boolean {
        return securePreferences.getBoolean("deeperInsightsUnlocked", false)
    }
}