package com.example.ratemyidea.network

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.HttpUrl
import okhttp3.RequestBody.Companion.toRequestBody

import org.json.JSONObject

import kotlinx.serialization.json.Json
import com.example.ratemyidea.network.model.Idea


class IdeaClient (
    private val client: OkHttpClient
) {
    suspend fun fetchIdeasForUser (
        userUuid: String
    ): List<Idea> = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {

        val request = Request.Builder()
            .get()
            .url("http://192.168.188.21:45002/ideas/${userUuid}")
            .build()

        try {
            client.newCall(request).execute().use { response ->
                val responseBody = response.body?.string() ?: ""
                return@withContext Json.decodeFromString<List<Idea>>(responseBody)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun fetchIdea (
        uuid: String
    ): Idea = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {

        val request = Request.Builder()
            .get()
            .url("http://192.168.188.21:45002/idea/random?exclude=$uuid")
            .build()

        try {
            client.newCall(request).execute().use { response ->
                val responseBody = response.body?.string() ?: ""
                return@withContext Json.decodeFromString<Idea>(responseBody)
            }
        } catch (e: Exception) {
            Idea("123", "Edible Spoons", "A spoon made from rice, barley or whatever that is edible.")
        }
    }

    suspend fun rateIdea (
        userUuid: String,
        idea: Idea,
        like: Boolean
    ): Unit = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {

        val url = HttpUrl.Builder()
            .scheme("http")
            .host("192.168.188.21")
            .port(45002)
            .addPathSegment("idea")
            .addPathSegment(if (like) "like" else "dislike")
            .addQueryParameter("userUuid", userUuid)
            .addQueryParameter("ideaUuid", idea.uuid)
            .build()

        val request = Request.Builder()
            .get()
            .url(url)
            .build()

        try {
            client.newCall(request).execute().use { response ->
                val responseBody = response.body?.string() ?: ""
            }
        } catch (e: Exception) {
            ""
        }
    }

    suspend fun updateIdea (
        idea: Idea
    ): Idea = kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {

        val mediaType = "application/json; charset=utf-8".toMediaType()

        val jsonRequestBody = JSONObject().apply {
            put("uuid", "")
            put("title", idea.title)
            put("description", idea.description)
            put("total", 0)
            put("likes", 0)
            put("dislikes", 0)
            put("userUuid", idea.userUuid)
        }.toString().toRequestBody(mediaType)

        val request = Request.Builder()
            .url("http://192.168.188.21:45002/idea/insert")
            .addHeader("Content-Type", "application/json")
            .post(jsonRequestBody)
            .build()

        try {
            client.newCall(request).execute().use { response ->
                val responseBody = response.body?.string() ?: ""
                val jsonObject = JSONObject(responseBody)

                val idea = Idea (
                    uuid = jsonObject.getString("uuid"),
                    title = jsonObject.getString("title"),
                    description = jsonObject.getString("description"),
                    total = jsonObject.getString("total").toInt(),
                    likes = jsonObject.getString("likes").toInt(),
                    dislikes = jsonObject.getString("dislikes").toInt(),
                    userUuid = jsonObject.getString("userUuid")
                )

                idea
            }
        } catch (e: Exception) {
            Idea("1", "Error", "Check your wifi connection!")
        }
    }
}