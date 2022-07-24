/*
 * StoryItemMapper.kt
 * Personal App Android
 * Created by Alan Hernández on 25/01/22 19:45
 */

package com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.mapper.characters

import com.voltek.marvelapp.core.domain.mapper.Transform
import com.voltek.marvelapp.core.extensions.value
import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.responses.characters.StoryItemDto
import com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters.StoryItemModel

class StoryItemMapper : Transform<StoryItemDto, StoryItemModel>() {

    override fun transform(value: StoryItemDto): StoryItemModel =
        with(value) { StoryItemModel(name.value(), resourceURI.value(), type.value()) }
}