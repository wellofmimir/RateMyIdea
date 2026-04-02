package com.example.ratemyidea.securepreferences

import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.content.Context
import kotlin.getValue
import androidx.core.content.edit

import androidx.security.crypto.MasterKey
import androidx.security.crypto.EncryptedSharedPreferences

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

    fun setUUID(uuid: String) {
        securePreferences.edit {
            putString("uuid", uuid)
        }
    }

    fun uuid(): String {
        return securePreferences.getString("uuid", "") ?: ""
    }

    fun setFirstIdeaInserted() {
        securePreferences.edit {
            putBoolean("firstIdeaInserted", true)
        }
    }

    fun firstIdeaInserted(): Boolean {
        return securePreferences.getBoolean("firstIdeaInserted", false)
    }

    fun incrementIdeasRated() {
        val currentIdeasRated = securePreferences.getInt("ideasRated", 0)
        securePreferences.edit {
            putInt("ideasRated", currentIdeasRated + 1)
        }
    }

    fun ideasRated(): Int {
        return securePreferences.getInt("ideasRated", 0)
    }
}