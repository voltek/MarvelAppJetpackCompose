/*
 * GetHeroDetail.kt
 * Personal App Android
 * Created by Alan Hernández on 25/01/22 21:30
 */

package com.voltek.marvelapp.feature_marvel_heroes.domain.use_case.hero_detail

import com.voltek.marvelapp.core.data.Resource
import com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters.CharacterResponseModel
import com.voltek.marvelapp.feature_marvel_heroes.domain.repository.MarvelCharacterRepository
import javax.inject.Inject

class GetHeroDetailUseCase @Inject constructor(private val repository: MarvelCharacterRepository) {

    suspend operator fun invoke(characterId: String): Resource<CharacterResponseModel> {
        return repository.getMarvelCharacter(characterId)
    }
}