/*
 * CharactersMocks.kt
 * Personal App Android
 * Created by Alan Hernández on 14/03/22 17:10
 */

package com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.mapper.characters.mocks

import com.voltek.marvelapp.core.data.Resource
import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.mapper.characters.*
import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.responses.characters.*
import com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters.*

class CharactersMocks {
    private val fakeResultMapper = ResultMapper(ItemMapper(), StoryItemMapper(), UrlMapper())

    fun getDataMapper() = DataMapper(fakeResultMapper)

    fun getCharacterResponseDto(): CharacterResponseDto {
        val itemDto = ItemDto(
            "Character Name",
            "https://www.marvel.com/1"
        )
        val resultDtoList = listOf(
            ResultDto(
                ComicsDto(
                    12,
                    "https://www.marvel.com/collection",
                    listOf(itemDto),
                    12
                ),
                "Character Description",
                EventsDto(
                    13,
                    "https://www.marvel.com/events",
                    listOf(itemDto),
                    13
                ),
                0,
                "10/10/2020",
                "Wolverine",
                "https://www.marvel.com",
                SeriesDto(
                    14,
                    "https://www.marvel.com/series",
                    listOf(itemDto),
                    14
                ),
                StoriesDto(
                    15,
                    "https://www.marvel.com/events",
                    listOf(
                        StoryItemDto(
                            "Character Name",
                            "https://www.marvel.com/2",
                            "Type"
                        )
                    ),
                    15
                ),
                ThumbnailDto(
                    ".jpg",
                    "http://i.annihil.us/u/prod/marvel/i/mg/3/40/4bb4680432f73"
                ),
                listOf()
            )
        )
        return CharacterResponseDto(
            "AttributeHtml",
            "AttributeText",
            200,
            "Copyright",
            DataDto(
                10,
                10,
                10,
                resultDtoList,
                10
            ),
            "ETag",
            "Status"
        )
    }

    fun getCharacterResponseModel(): CharacterResponseModel {
        val itemModel = ItemModel(
            "Character Name",
            "https://www.marvel.com/1"
        )
        val resultModelList = listOf(
            ResultModel(
                ComicsModel(
                    12,
                    "https://www.marvel.com/collection",
                    listOf(itemModel),
                    12
                ),
                "Character Description",
                EventsModel(
                    13,
                    "https://www.marvel.com/events",
                    listOf(itemModel),
                    13
                ),
                0,
                "10/10/2020",
                "Wolverine",
                "https://www.marvel.com",
                SeriesModel(
                    14,
                    "https://www.marvel.com/series",
                    listOf(itemModel),
                    14
                ),
                StoriesModel(
                    15,
                    "https://www.marvel.com/events",
                    listOf(
                        StoryItemModel(
                            "Character Name",
                            "https://www.marvel.com/2",
                            "Type"
                        )
                    ),
                    15
                ),
                ThumbnailModel(
                    ".jpg",
                    "http://i.annihil.us/u/prod/marvel/i/mg/3/40/4bb4680432f73"
                ),
                listOf()
            )
        )

        return CharacterResponseModel(
            "AttributeHtml",
            "AttributeText",
            200,
            "Copyright",
            DataModel(
                10,
                10,
                10,
                resultModelList,
                10
            ),
            "ETag",
            "Status"
        )
    }

    fun getResourceSuccess(): Resource<CharacterResponseModel> {
        return Resource.Success(getCharacterResponseModel())
    }

    fun getResourceError(): Resource<CharacterResponseModel> {
        return Resource.Error(UNKNOWN_ERROR)
    }

    companion object {
        private const val UNKNOWN_ERROR = "unknown_error"
    }
}