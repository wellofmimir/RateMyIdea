package com.example.ratemyidea.network

import kotlinx.serialization.Serializable
import okhttp3.OkHttpClient
import okhttp3.Request

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
data class Idea (
    val uuid: String,
    val title: String,
    val description: String
)

class FetchIdeaClient (
    private val client: OkHttpClient
) {
    suspend fun fetchIdea(): Idea = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {

        val request = Request.Builder()
            .get()
            .url("https://webhook.site/7ff202e8-f379-492c-a3f9-f6f1ba580ea6")
            .build()

        try {
            client.newCall(request).execute().use { response ->
                val responseBody = response.body?.string() ?: ""
                return@withContext Json.decodeFromString<Idea>(responseBody)
            }
        } catch (e: Exception) {
            Idea("", "", "")
        }
    }
}