package com.example.covid_tracker

import okhttp3.OkHttpClient
import okhttp3.Request

object Client {  //Single term class no need to make object

    private val okHttpclient = OkHttpClient()
    private val request = Request.Builder()
        .url("https://api.covid19india.org/data.json")
        .build()
    val api = okHttpclient.newCall(request)

}