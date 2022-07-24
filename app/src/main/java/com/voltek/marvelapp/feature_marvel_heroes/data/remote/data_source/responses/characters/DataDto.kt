/*
 * Data.kt
 * Personal App Android
 * Created by Alan Hernández on 17/01/22 15:01
 */

package com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.responses.characters

data class DataDto(
    val count: Int?,
    val limit: Int?,
    val offset: Int?,
    val results: List<ResultDto>?,
    val total: Int?
)