package com.example.ratemyidea.network

import okhttp3.OkHttpClient

object SharedHttpClient {
    val sharedClient = OkHttpClient()
}