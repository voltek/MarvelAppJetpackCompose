/*
 * CharacterResponseMapper.kt
 * Personal App Android
 * Created by Alan Hernández on 25/01/22 19:41
 */
package com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.mapper.characters

import com.voltek.marvelapp.core.domain.mapper.Transform
import com.voltek.marvelapp.core.extensions.value
import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.responses.characters.CharacterResponseDto
import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.responses.characters.DataDto
import com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters.CharacterResponseModel
import com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters.DataModel
import javax.inject.Inject

class CharacterResponseMapper @Inject constructor(
    private val dataMapper: Transform<DataDto?, DataModel>
) : Transform<CharacterResponseDto, CharacterResponseModel>() {

    override fun transform(value: CharacterResponseDto): CharacterResponseModel {
        return with(value) {
            CharacterResponseModel(
                attributionHTML.value(),
                attributionText.value(),
                code.value(),
                copyright.value(),
                dataMapper.transform(data),
                etag.value(),
                status.value(),
            )
        }
    }
}