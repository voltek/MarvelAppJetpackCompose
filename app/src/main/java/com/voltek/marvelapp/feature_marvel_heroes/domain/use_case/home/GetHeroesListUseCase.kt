/*
 * GetHeroesListUseCase.kt
 * Personal App Android
 * Created by Alan Hernández on 25/01/22 20:42
 */

package com.voltek.marvelapp.feature_marvel_heroes.domain.use_case.home

import com.voltek.marvelapp.core.data.Resource
import com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters.CharacterResponseModel
import com.voltek.marvelapp.feature_marvel_heroes.domain.repository.MarvelCharacterRepository
import javax.inject.Inject

class GetHeroesListUseCase @Inject constructor(private val repository: MarvelCharacterRepository) {

    suspend operator fun invoke(limit: Int, offset: Int): Resource<CharacterResponseModel> {
        return repository.getMarvelCharactersList(limit, offset)
    }
}