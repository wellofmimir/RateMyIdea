package com.example.ratemyidea.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Idea (
    val uuid: String,
    val title: String,
    val description: String,
    val total: Int = 0,
    val likes: Int = 0,
    val dislikes: Int = 0,
    var userUuid: String = ""
)
