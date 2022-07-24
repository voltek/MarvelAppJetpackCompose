package com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters

import com.voltek.marvelapp.core.util.Constants.EMPTY_STRING
import com.voltek.marvelapp.core.util.Constants.ZERO

data class StoriesModel(
    val available: Int= ZERO,
    val collectionURI: String = EMPTY_STRING,
    val storyItemModels: List<StoryItemModel> = listOf(),
    val returned: Int= ZERO
)