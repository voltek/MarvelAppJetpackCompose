/*
 * ItemMapper.kt
 * Personal App Android
 * Created by Alan Hernández on 25/01/22 19:44
 */

package com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.mapper.characters

import com.voltek.marvelapp.core.domain.mapper.Transform
import com.voltek.marvelapp.core.extensions.value
import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.responses.characters.ItemDto
import com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters.ItemModel

class ItemMapper : Transform<ItemDto, ItemModel>() {

    override fun transform(value: ItemDto): ItemModel =
        with(value) { ItemModel(name.value(), resourceURI.value()) }
}