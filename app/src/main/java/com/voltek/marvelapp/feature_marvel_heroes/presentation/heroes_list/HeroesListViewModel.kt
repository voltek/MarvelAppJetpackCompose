/*
 * HeroesListViewModel.kt
 * Personal App Android
 * Created by Alan Hernández on 25/01/22 21:50
 */

package com.voltek.marvelapp.feature_marvel_heroes.presentation.heroes_list

import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voltek.marvelapp.core.data.Resource
import com.voltek.marvelapp.core.extensions.value
import com.voltek.marvelapp.core.util.Constants.EMPTY_STRING
import com.voltek.marvelapp.core.util.Constants.ZERO
import com.voltek.marvelapp.core.presentation.dispatchers.DispatcherProvider
import com.voltek.marvelapp.feature_marvel_heroes.domain.model.characters.ResultModel
import com.voltek.marvelapp.feature_marvel_heroes.domain.use_case.home.GetHeroesListUseCase
import com.voltek.marvelapp.feature_marvel_heroes.domain.use_case.images.CalcDominantColorUseCase
import com.voltek.marvelapp.feature_marvel_heroes.presentation.util.PresentationUtils.PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroesListViewModel @Inject constructor(
    private val getHeroesListUseCase: GetHeroesListUseCase,
    private val getCalcDominantColorUseCase: CalcDominantColorUseCase,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    var heroesList = mutableStateOf<List<ResultModel>>(listOf())
    var loadError = mutableStateOf(EMPTY_STRING)
    var isLoading = mutableStateOf(false)
    var endReach = mutableStateOf(false)
    var isSearching = mutableStateOf(false)

    private var currentPage = ZERO
    private var cachedCharactersList = listOf<ResultModel>()
    private var isSearchStarting = true

    init {
        loadHeroesPaginated()
    }

    fun searchCharactersList(query: String) {
        val listToSearch = if (isSearchStarting) {
            heroesList.value
        } else {
            cachedCharactersList
        }

        viewModelScope.launch(dispatchers.default) {
            if (query.isEmpty()) {
                heroesList.value = cachedCharactersList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }

            val results = listToSearch.filter {
                it.name.contains(query.trim(), ignoreCase = true)
            }

            if(isSearchStarting){
                cachedCharactersList = heroesList.value
                isSearchStarting = false
            }

            heroesList.value = results
            isSearching.value = true
        }
    }

    fun loadHeroesPaginated() {
        isLoading.value = true

        viewModelScope.launch {
            when (val result = getHeroesListUseCase.invoke(PAGE_SIZE, getOffset())) {
                is Resource.Success -> {
                    if (result.data?.dataModel != null) {
                        updateEndReach(result.data.dataModel.total.value())
                        updateVariablesStates(result.data.dataModel.resultModels)
                    }
                }
                is Resource.Error -> {
                    loadError.value = result.message.value()
                    isLoading.value = false
                }
                else -> {
                    isLoading.value = false
                }
            }
        }
    }

    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        getCalcDominantColorUseCase.invoke(drawable, onFinish)
    }

    private fun getOffset(): Int {
        return currentPage * PAGE_SIZE
    }

    private fun updateEndReach(listCount: Int) {
        endReach.value = currentPage * PAGE_SIZE >= listCount
    }

    private fun updateVariablesStates(heroesEntries: List<ResultModel>) {
        currentPage++
        loadError.value = EMPTY_STRING
        isLoading.value = false
        heroesList.value += heroesEntries
    }
}