package com.molokosoft.ratemyidea.billing

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object CreditCatalog {

    private val internDeeperInsightsPurchased = MutableSharedFlow<Boolean> (
        replay = 0,
        extraBufferCapacity = 1
    )

    val deeperInsightsPurchased = internDeeperInsightsPurchased.asSharedFlow()

    suspend fun deeperInsightsPurchased(success: Boolean) {
        internDeeperInsightsPurchased.emit(success)
    }
}