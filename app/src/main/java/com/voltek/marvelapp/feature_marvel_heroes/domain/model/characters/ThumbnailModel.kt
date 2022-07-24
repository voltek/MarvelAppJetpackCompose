package com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters

import com.voltek.marvelapp.core.util.Constants.EMPTY_STRING

data class ThumbnailModel(
    val extension: String = EMPTY_STRING,
    val path: String= EMPTY_STRING
)