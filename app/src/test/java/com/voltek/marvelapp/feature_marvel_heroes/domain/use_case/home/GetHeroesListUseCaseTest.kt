/*
 * GetHeroesListUseCaseTest.kt
 * Personal App Android
 * Created by Alan Hernández on 26/03/22 13:03
 */

package com.voltek.marvelapp.feature_marvel_heroes.domain.use_case.home

import com.voltek.marvelapp.core.data.Resource
import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.mapper.characters.mocks.CharactersMocks
import com.voltek.marvelapp.feature_marvel_heroes.domain.repository.MarvelCharacterRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class GetHeroesListUseCaseTest() {

    @Mock
    private lateinit var repository: MarvelCharacterRepository

    private val getHeroDetailUseCase:
            GetHeroesListUseCase by lazy { GetHeroesListUseCase(repository) }
    private val characterMocks = CharactersMocks()

    @ExperimentalCoroutinesApi
    @Test
    fun `Get HeroesListUseCase Success`() {
        runTest {
            Mockito.`when`(repository.getMarvelCharactersList(Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(characterMocks.getResourceSuccess())
            val result = getHeroDetailUseCase.invoke(10, 10)

            assertTrue(result is Resource.Success)
            assertTrue(result.message == null)
            assertFalse(result.data == null)
            assertFalse(result.data?.dataModel == null)
            Mockito.verify(repository, Mockito.times(1))
                .getMarvelCharactersList(Mockito.anyInt(), Mockito.anyInt())
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Get HeroesListUseCase Error`() {
        runTest {
            Mockito.`when`(repository.getMarvelCharactersList(10, 10))
                .thenReturn(characterMocks.getResourceError())

            val result = getHeroDetailUseCase.invoke(10, 10)

            assertTrue(result is Resource.Error)
            assertTrue(result.data == null)
            assertFalse(result.message == null)
            assertTrue(result.message?.isNotEmpty() == true)
            Mockito.verify(repository, Mockito.times(1))
                .getMarvelCharactersList(10, 10)
        }
    }
}