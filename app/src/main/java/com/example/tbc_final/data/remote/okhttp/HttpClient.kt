package com.example.tbc_final.data.remote.okhttp

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient

object HttpClient {

    private const val API_KEY = "e29648fbfcmsh648c9d265aa6dfep1f40e1jsn4a9bf7abaa3f"
    private const val API_HOST = "exercisedb.p.rapidapi.com"

    val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor{apiKeyAsHeader(it = it)}
        .build()



    private fun apiKeyAsHeader(it: Interceptor.Chain) = it.proceed(
        it.request()
            .newBuilder()
            .addHeader("X-RapidAPI-Key", API_KEY)
            .addHeader("X-RapidAPI-Host", API_HOST)
            .build()
    )


}