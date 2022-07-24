/*
 * ResultMapper.kt
 * Personal App Android
 * Created by Alan Hernández on 25/01/22 19:43
 */

package com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.mapper.characters

import com.voltek.marvelapp.core.domain.mapper.Transform
import com.voltek.marvelapp.core.extensions.value
import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.responses.characters.ItemDto
import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.responses.characters.ResultDto
import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.responses.characters.StoryItemDto
import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.responses.characters.UrlDto
import com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters.*
import javax.inject.Inject

class ResultMapper @Inject constructor(
    private val itemMapper: Transform<ItemDto, ItemModel>,
    private val storyItemMapper: Transform<StoryItemDto, StoryItemModel>,
    private val urlMapper: Transform<UrlDto, UrlModel>
) : Transform<ResultDto, ResultModel>() {

    override fun transform(value: ResultDto): ResultModel {
        return with(value) {
            ResultModel(
                ComicsModel(
                    comics?.available.value(),
                    comics?.collectionURI.value(),
                    itemMapper.transformCollection(comics?.items ?: listOf()),
                    comics?.returned.value()
                ),
                description.value(),
                EventsModel(
                    events?.available.value(),
                    events?.collectionURI.value(),
                    itemMapper.transformCollection(events?.items ?: listOf()),
                    events?.returned.value()
                ),
                id.value(),
                modified.value(),
                name.value(),
                resourceURI.value(),
                SeriesModel(
                    series?.available.value(),
                    series?.collectionURI.value(),
                    itemMapper.transformCollection(series?.items ?: listOf()),
                    series?.returned.value()
                ),
                StoriesModel(
                    stories?.available.value(),
                    stories?.collectionURI.value(),
                    storyItemMapper.transformCollection(stories?.items ?: listOf()),
                    stories?.returned.value()
                ),
                ThumbnailModel(thumbnail?.extension.value(), thumbnail?.path.value()),
                urlMapper.transformCollection(urls ?: listOf())
            )
        }
    }
}