package com.molokosoft.ratemyidea.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class IdeaRatingAlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        IdeaRatingWorker.enqueue(context)
        IdeaRatingAlarmScheduler.schedule(context)
    }
}