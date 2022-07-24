/*
 * DataMapper.kt
 * Personal App Android
 * Created by Alan Hernández on 25/01/22 19:41
 */

package com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.mapper.characters

import com.voltek.marvelapp.core.domain.mapper.Transform
import com.voltek.marvelapp.core.extensions.value
import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.responses.characters.DataDto
import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.responses.characters.ResultDto
import com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters.DataModel
import com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters.ResultModel
import javax.inject.Inject

class DataMapper @Inject constructor(private val resultMapper: Transform<ResultDto, ResultModel>) :
    Transform<DataDto?, DataModel>() {

    override fun transform(value: DataDto?): DataModel {
        return value?.let {
            DataModel(
                it.count.value(),
                it.limit.value(),
                it.offset.value(),
                resultMapper.transformCollection(it.results ?: listOf()),
                it.total.value(),
            )
        } ?: DataModel()
    }
}