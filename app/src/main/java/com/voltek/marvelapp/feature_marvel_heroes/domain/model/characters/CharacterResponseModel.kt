/*
 * CharacterResponse.kt
 * Personal App Android
 * Created by Alan Hernández on 17/01/22 14:49
 */

package com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters

data class CharacterResponseModel(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val dataModel: DataModel,
    val eTag: String,
    val status: String
)