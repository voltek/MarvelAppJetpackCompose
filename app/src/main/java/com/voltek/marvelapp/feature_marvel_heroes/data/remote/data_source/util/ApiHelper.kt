/*
 * ApiHelper.kt
 * Personal App Android
 * Created by Alan Hernández on 17/01/22 15:01
 */

package com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.util

import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object ApiHelper {

    const val TIME_STAMP = "ts"
    const val API_KEY = "apikey"
    const val HASH = "hash"

    private const val TIME_OUT = 30L

    private val httpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    fun getOkHttpClientWithStethoAndLoggingInterceptor() =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor(StethoInterceptor())
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .build()
}