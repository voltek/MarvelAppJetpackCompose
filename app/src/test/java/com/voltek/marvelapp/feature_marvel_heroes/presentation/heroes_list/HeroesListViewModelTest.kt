/*
 * HeroesListViewModelTest.kt
 * Personal App Android
 * Created by Alan Hernández on 26/03/22 17:00
 */

package com.voltek.marvelapp.feature_marvel_heroes.presentation.heroes_list

import com.voltek.marvelapp.core.presentation.dispatchers.StandardDispatchersTest
import com.voltek.marvelapp.feature_marvel_heroes.domain.use_case.home.GetHeroesListUseCase
import com.voltek.marvelapp.feature_marvel_heroes.domain.use_case.images.CalcDominantColorUseCase
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock

class HeroesListViewModelTest() {

    @Mock
    private lateinit var getHeroesListUseCase: GetHeroesListUseCase

    private val calcDominant by lazy { mock(CalcDominantColorUseCase::class.java) }
    private val standardDispatcherTest by lazy { StandardDispatchersTest() }

    val viewModel by lazy {
        HeroesListViewModel(getHeroesListUseCase,calcDominant,standardDispatcherTest)
    }

    @Test
    fun test() {

    }
}