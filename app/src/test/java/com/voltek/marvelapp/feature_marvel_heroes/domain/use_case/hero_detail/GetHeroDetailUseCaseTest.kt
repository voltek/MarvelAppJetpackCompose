/*
 * GetHeroDetailUseCaseTest.kt
 * Personal App Android
 * Created by Alan Hernández on 18/03/22 13:52
 */

package com.voltek.marvelapp.feature_marvel_heroes.domain.use_case.hero_detail

import com.voltek.marvelapp.core.data.Resource
import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.mapper.characters.mocks.CharactersMocks
import com.voltek.marvelapp.feature_marvel_heroes.data.repository.MarvelCharacterRepositoryImpl
import com.voltek.marvelapp.feature_marvel_heroes.domain.repository.MarvelCharacterRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class GetHeroDetailUseCaseTest {

    @Mock
    private lateinit var repository: MarvelCharacterRepository

    private val getHeroDetailUseCase:
            GetHeroDetailUseCase by lazy { GetHeroDetailUseCase(repository) }
    private val characterMocks = CharactersMocks()

    @ExperimentalCoroutinesApi
    @Test
    fun `Get HeroDetailUseCase Success`() {
        runTest {
            `when`(repository.getMarvelCharacter(anyString()))
                .thenReturn(characterMocks.getResourceSuccess())
            val result = getHeroDetailUseCase.invoke("10")

            Assert.assertTrue(result is Resource.Success)
            Assert.assertTrue(result.message == null)
            Assert.assertFalse(result.data == null)
            Assert.assertFalse(result.data?.dataModel == null)
            verify(repository, times(1)).getMarvelCharacter(anyString())
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Get HeroDetailUseCase Error`() {
        runTest {
            `when`(repository.getMarvelCharacter(anyString()))
                .thenReturn(characterMocks.getResourceError())
            val result = getHeroDetailUseCase.invoke("10")

            Assert.assertTrue(result is Resource.Error)
            Assert.assertTrue(result.data == null)
            Assert.assertFalse(result.message == null)
            Assert.assertTrue(result.message?.isNotEmpty() == true)
            verify(repository, times(1)).getMarvelCharacter(anyString())
        }
    }
}