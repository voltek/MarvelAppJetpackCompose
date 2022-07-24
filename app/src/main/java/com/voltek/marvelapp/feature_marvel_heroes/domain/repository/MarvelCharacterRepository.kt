package com.voltek.marvelapp.feature_marvel_heroes.domain.repository

import com.voltek.marvelapp.core.data.Resource
import com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters.CharacterResponseModel

interface MarvelCharacterRepository {

    suspend fun getMarvelCharactersList(limit: Int, offset: Int): Resource<CharacterResponseModel>

    suspend fun getMarvelCharacter(characterId: String): Resource<CharacterResponseModel>
}