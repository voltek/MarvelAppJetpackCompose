package com.voltek.marvelapp.feature_marvel_heroes.data.repository

import com.voltek.marvelapp.core.data.Resource
import com.voltek.marvelapp.core.domain.mapper.Transform
import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.MarvelApi
import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.responses.characters.CharacterResponseDto
import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.util.ApiEndpoint
import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.util.HashBuilder
import com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters.CharacterResponseModel
import com.voltek.marvelapp.feature_marvel_heroes.domain.repository.MarvelCharacterRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MarvelCharacterRepositoryImpl @Inject constructor(
    private val apiInstance: MarvelApi,
    private val characterResponseMapper: Transform<CharacterResponseDto, CharacterResponseModel>
) : MarvelCharacterRepository {

    override suspend fun getMarvelCharactersList(limit: Int, offset: Int):
            Resource<CharacterResponseModel> {

        val response = try {
            HashBuilder().build().run {
                apiInstance.getMarvelCharactersList(
                    limit, offset, timeStamp, ApiEndpoint.PUBLIC_API_KEY, hash
                )
            }
        } catch (e: Exception) {
            return Resource.Error("Unknown Error: ${e.message}")
        }

        return Resource.Success(characterResponseMapper.transform(response))
    }

    override suspend fun getMarvelCharacter(characterId: String): Resource<CharacterResponseModel> {
        val response = try {
            HashBuilder().build().run {
                apiInstance.getMarvelCharacter(
                    characterId, timeStamp,
                    ApiEndpoint.PUBLIC_API_KEY, hash
                )
            }
        } catch (e: Exception) {
            return Resource.Error("Unknown Error: ${e.message}")
        }

        return Resource.Success(characterResponseMapper.transform(response))
    }
}