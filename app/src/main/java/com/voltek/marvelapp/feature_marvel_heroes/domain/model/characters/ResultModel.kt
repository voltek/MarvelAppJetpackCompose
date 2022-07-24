package com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters

import com.voltek.marvelapp.core.util.Constants.EMPTY_STRING
import com.voltek.marvelapp.core.util.Constants.ZERO

data class ResultModel(
    val comicsModel: ComicsModel = ComicsModel(),
    val description: String = EMPTY_STRING,
    val eventsModel: EventsModel = EventsModel(),
    val id: Int = ZERO,
    val modified: String = EMPTY_STRING,
    val name: String = EMPTY_STRING,
    val resourceURI: String = EMPTY_STRING,
    val seriesModel: SeriesModel = SeriesModel(),
    val storiesModel: StoriesModel = StoriesModel(),
    val thumbnailModel: ThumbnailModel = ThumbnailModel(),
    val urlModels: List<UrlModel> = listOf()
)