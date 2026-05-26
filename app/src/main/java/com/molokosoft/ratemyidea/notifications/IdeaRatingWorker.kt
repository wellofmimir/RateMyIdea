package com.molokosoft.ratemyidea.notifications

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.content.Context
import androidx.work.*
import com.molokosoft.ratemyidea.network.IdeaClient
import com.molokosoft.ratemyidea.network.SharedHttpClient
import com.molokosoft.ratemyidea.securepreferences.SecurePreferences
import java.util.concurrent.TimeUnit
import com.molokosoft.ratemyidea.database.Database

class IdeaRatingWorker (
    context: Context,
    workerParams: WorkerParameters
): CoroutineWorker (context, workerParams) {

    companion object {
        fun enqueue(context: Context) {
            val request = OneTimeWorkRequestBuilder<IdeaRatingWorker>()
                .setConstraints (
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .setBackoffCriteria (
                    BackoffPolicy.LINEAR,
                    5,
                    TimeUnit.MINUTES
                )
                .build()

            WorkManager.getInstance(context)
                .enqueueUniqueWork (
                    "ideas_rating",
                    ExistingWorkPolicy.KEEP,
                    request
                )
        }
    }

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val ideaClient = IdeaClient(SharedHttpClient.sharedClient)
            val securePreferences = SecurePreferences(applicationContext)
            val database = Database(applicationContext)

            val uuid = securePreferences.uuid()
            val currentIdeasForUser = database.getAllIdeas()
            val ideasForUser = ideaClient.fetchIdeasForUser(uuid)
            val notifier = Notifier(applicationContext)

            ideasForUser.forEach outerForEach@ { newIdea ->
                currentIdeasForUser.forEach { oldIdea ->
                    if (newIdea.total != oldIdea.total) {
                        notifier.sendIdeaRatedNotification()
                        return@outerForEach
                    }
                }
            }

            Result.success()
        } catch (e: Exception) {
            if (runAttemptCount >= 10)
                Result.failure()
            else
                Result.retry()
        }
    }
}