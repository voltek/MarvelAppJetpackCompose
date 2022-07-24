/*
 * MarvelApi.kt
 * Personal App Android
 * Created by Alan Hernández on 17/01/22 15:01
 */

package com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source


import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.responses.characters.CharacterResponseDto
import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.util.ApiEndpoint
import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.util.ApiHelper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET(ApiEndpoint.CHARACTERS_URL)
    suspend fun getMarvelCharactersList(
        @Query(LIMIT) limit: Int,
        @Query(OFFSET) offset: Int,
        @Query(ApiHelper.TIME_STAMP) timeStamp: String,
        @Query(ApiHelper.API_KEY) publicApiKey: String,
        @Query(ApiHelper.HASH) hash: String
    ): CharacterResponseDto

    @GET(ApiEndpoint.CHARACTERS_DETAIL_URL)
    suspend fun getMarvelCharacter(
        @Path(CHARACTER_ID) characterId: String,
        @Query(ApiHelper.TIME_STAMP) timeStamp: String,
        @Query(ApiHelper.API_KEY) publicApiKey: String,
        @Query(ApiHelper.HASH) hash: String
    ): CharacterResponseDto

    companion object {
        private const val LIMIT = "limit"
        private const val OFFSET = "offset"

        private const val CHARACTER_ID = "character_id"
    }
}