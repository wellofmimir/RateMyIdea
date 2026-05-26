package com.molokosoft.ratemyidea.network

import okhttp3.OkHttpClient

object SharedHttpClient {
    val sharedClient = OkHttpClient()
}