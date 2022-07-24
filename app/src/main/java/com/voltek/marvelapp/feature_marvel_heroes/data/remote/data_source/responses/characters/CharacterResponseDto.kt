/*
 * CharacterResponse.kt
 * Personal App Android
 * Created by Alan Hernández on 17/01/22 15:01
 */

package com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.responses.characters

data class CharacterResponseDto(
    val attributionHTML: String?,
    val attributionText: String?,
    val code: Int?,
    val copyright: String?,
    val data: DataDto?,
    val etag: String?,
    val status: String?
)