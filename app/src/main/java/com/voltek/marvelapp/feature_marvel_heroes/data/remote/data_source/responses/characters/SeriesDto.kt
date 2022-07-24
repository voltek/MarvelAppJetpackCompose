/*
 * Series.kt
 * Personal App Android
 * Created by Alan Hernández on 17/01/22 15:01
 */

package com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.responses.characters

data class SeriesDto(
    val available: Int?,
    val collectionURI: String?,
    val items: List<ItemDto>?,
    val returned: Int?
)