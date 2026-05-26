package com.molokosoft.ratemyidea.network

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.HttpUrl
import okhttp3.RequestBody.Companion.toRequestBody

import org.json.JSONObject

import kotlinx.serialization.json.Json
import com.molokosoft.ratemyidea.network.model.Idea
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private val json = Json {
    ignoreUnknownKeys = true
}

class IdeaClient (
    private val client: OkHttpClient
) {
    suspend fun fetchIdeasForUser (
        userUuid: String
    ): List<Idea> = withContext(Dispatchers.IO) {

        val request = Request.Builder()
            .get()
            .url("https://greeen-app.com/rmi/api/ideas/${userUuid}")
            .build()

        try {
            client.newCall(request).execute().use { response ->
                val responseBody = response.body?.string() ?: ""
                return@withContext json.decodeFromString<List<Idea>>(responseBody)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun fetchIdea (
        uuid: String
    ): Idea = withContext(Dispatchers.IO) {

        val request = Request.Builder()
            .get()
            .url("https://greeen-app.com/rmi/api/idea/random?exclude=$uuid")
            .build()

        try {
            client.newCall(request).execute().use { response ->
                val responseBody = response.body?.string() ?: ""
                return@withContext json.decodeFromString<Idea>(responseBody)
            }
        } catch (e: Exception) {
            Idea("123", "Edible Spoons", "A spoon made from rice, barley or whatever that is edible.")
        }
    }

    suspend fun rateIdea (
        userUuid: String,
        gender: String,
        ageBracket: String,
        idea: Idea,
        like: Boolean
    ): Unit = withContext(Dispatchers.IO) {

        val url = HttpUrl.Builder()
            .scheme("https")
            .host("greeen-app.com")
            .addPathSegment("rmi")
            .addPathSegment("api")
            .addPathSegment("idea")
            .addPathSegment(if (like) "like" else "dislike")
            .addQueryParameter("userUuid", userUuid)
            .addQueryParameter("ideaUuid", idea.uuid)
            .addQueryParameter("gender", gender)
            .addQueryParameter("ageBracket", ageBracket)
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
    ): Idea = withContext(Dispatchers.IO) {

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
            .url("https://greeen-app.com/rmi/api/idea/insert")
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
                    maleEighteenToTwentyFour = jsonObject.getString("maleEighteenToTwentyFour").toInt(),
                    femaleEighteenToTwentyFour = jsonObject.getString("femaleEighteenToTwentyFour").toInt(),

                    maleTwentyFiveToThirtyFour = jsonObject.getString("maleTwentyFiveToThirtyFour").toInt(),
                    femaleTwentyFiveToThirtyFour = jsonObject.getString("femaleTwentyFiveToThirtyFour").toInt(),

                    maleThirtyFiveToFortyFour = jsonObject.getString("maleThirtyFiveToFortyFour").toInt(),
                    femaleThirtyFiveToFortyFour = jsonObject.getString("femaleThirtyFiveToFortyFour").toInt(),

                    maleFortyFiveToFiftyFour = jsonObject.getString("maleFortyFiveToFiftyFour").toInt(),
                    femaleFortyFiveToFiftyFour = jsonObject.getString("femaleFortyFiveToFiftyFour").toInt(),

                    maleFiftyFiveToSixtyFour = jsonObject.getString("maleFiftyFiveToSixtyFour").toInt(),
                    femaleFiftyFiveToSixtyFour = jsonObject.getString("femaleFiftyFiveToSixtyFour").toInt(),

                    maleSixtyFivePlus = jsonObject.getString("maleSixtyFivePlus").toInt(),
                    femaleSixtyFivePlus = jsonObject.getString("femaleSixtyFivePlus").toInt(),

                    male = jsonObject.getString("male").toInt(),
                    female = jsonObject.getString("female").toInt(),

                    userUuid = jsonObject.getString("userUuid")
                )

                idea
            }
        } catch (e: Exception) {
            Idea("1", "Error", "Check your wifi connection!")
        }
    }
}