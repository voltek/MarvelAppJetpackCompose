/*
 * HashBuilder.kt
 * Personal App Android
 * Created by Alan Hernández on 17/01/22 15:01
 */

package com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.util

import com.voltek.marvelapp.core.util.Constants.EMPTY_STRING
import java.math.BigInteger
import java.security.MessageDigest

class HashBuilder {

    var timeStamp: String = EMPTY_STRING
    var hash: String = EMPTY_STRING

    fun build(): HashBuilder {
        timeStamp = getCurrentTimeStampInSeconds()
        hash = getMD5Hash(timeStamp)
        return this
    }

    private fun getCurrentTimeStampInSeconds(): String {
        return (System.currentTimeMillis() / SECONDS).toString()
    }

    private fun getMD5Hash(timeStamp: String): String {
        val inputString: String =
            timeStamp + ApiEndpoint.PRIVATE_API_KEY + ApiEndpoint.PUBLIC_API_KEY
        val md = MessageDigest.getInstance(MD5_ALGORITHM)
        return BigInteger(1, md.digest(inputString.toByteArray()))
            .toString(16).padStart(32, '0')
    }

    companion object {
        private const val SECONDS = 1000
        private const val MD5_ALGORITHM = "MD5"
    }
}