package com.molokosoft.ratemyidea.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Idea (
    val uuid: String = "",
    val title: String,
    val description: String,
    val total: Int = 0,
    val likes: Int = 0,
    val dislikes: Int = 0,
    var userUuid: String = "",

    val maleEighteenToTwentyFour: Int = 0,
    val femaleEighteenToTwentyFour: Int = 0,

    val maleTwentyFiveToThirtyFour: Int = 0,
    val femaleTwentyFiveToThirtyFour: Int = 0,

    val maleThirtyFiveToFortyFour: Int = 0,
    val femaleThirtyFiveToFortyFour: Int = 0,

    val maleFortyFiveToFiftyFour: Int = 0,
    val femaleFortyFiveToFiftyFour: Int = 0,

    val maleFiftyFiveToSixtyFour: Int = 0,
    val femaleFiftyFiveToSixtyFour: Int = 0,

    val maleSixtyFivePlus: Int = 0,
    val femaleSixtyFivePlus: Int = 0,

    val male: Int = 0,
    val female: Int = 0,
)
