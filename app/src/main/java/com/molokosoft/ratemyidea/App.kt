package com.molokosoft.ratemyidea

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import com.molokosoft.ratemyidea.notifications.IdeaRatingAlarmScheduler

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        IdeaRatingAlarmScheduler.schedule(this)
    }
}