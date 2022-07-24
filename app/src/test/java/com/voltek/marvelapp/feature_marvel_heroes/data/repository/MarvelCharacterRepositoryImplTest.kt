/*
 * MarvelCharacterRepositoryImplTest.kt
 * Personal App Android
 * Created by Alan Hernández on 15/03/22 17:22
 */

package com.voltek.marvelapp.feature_marvel_heroes.data.repository

import com.voltek.marvelapp.core.data.Resource
import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.MarvelApi
import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.mapper.characters.*
import com.voltek.marvelapp.feature_marvel_heroes.data.remote.data_source.mapper.characters.mocks.CharactersMocks
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.StrictStubs::class)
class MarvelCharacterRepositoryImplTest {

    private val marvelDataSource by lazy { mock(MarvelApi::class.java) }

    private val characterResponseMapper by lazy {
        CharacterResponseMapper(
            DataMapper(
                ResultMapper(ItemMapper(), StoryItemMapper(), UrlMapper())
            )
        )
    }

    private val repository: MarvelCharacterRepositoryImpl by lazy {
        MarvelCharacterRepositoryImpl(
            marvelDataSource,
            characterResponseMapper
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Get marvel character list Success`() {
        runTest {
            Mockito.`when`(
                marvelDataSource.getMarvelCharactersList(
                    anyInt(),
                    anyInt(),
                    anyString(),
                    anyString(),
                    anyString()
                )
            ).thenReturn(CharactersMocks().getCharacterResponseDto())

            val response = repository.getMarvelCharactersList(10, 0)

            Assert.assertTrue(response is Resource.Success)
            Assert.assertTrue(response.data != null)
            Assert.assertTrue(response.data?.code != null)
            Assert.assertTrue(response.data?.code == SUCCESS_CODE)
            Assert.assertTrue(response.data?.dataModel != null)

        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Get marvel character list with exception`() {
        runTest {
            given(
                marvelDataSource.getMarvelCharactersList(
                    anyInt(),
                    anyInt(),
                    anyString(),
                    anyString(),
                    anyString()
                )
            ).willAnswer {
                throw Exception(EXCEPTION_NAME)
            }

            val response = repository.getMarvelCharactersList(10, 0)

            Assert.assertTrue(response is Resource.Error)
            Assert.assertTrue(response.data == null)
            Assert.assertTrue(response.message?.isNotEmpty()!!)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Get Marvel Character Success`() {
        runTest {
            Mockito.`when`(
                marvelDataSource.getMarvelCharacter(
                    anyString(),
                    anyString(),
                    anyString(),
                    anyString()
                )
            ).thenReturn(CharactersMocks().getCharacterResponseDto())

            val response = repository.getMarvelCharacter("30")

            Assert.assertTrue(response is Resource.Success)
            Assert.assertTrue(response.data != null)
            Assert.assertTrue(response.data?.code != null)
            Assert.assertTrue(response.data?.code == SUCCESS_CODE)
            Assert.assertTrue(response.data?.dataModel != null)

        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `Get marvel character with exception`() {
        runTest {
            given(
                marvelDataSource.getMarvelCharacter(
                    anyString(),
                    anyString(),
                    anyString(),
                    anyString()
                )
            ).willAnswer {
                throw Exception(EXCEPTION_NAME)
            }

            val response = repository.getMarvelCharacter("30")

            Assert.assertTrue(response is Resource.Error)
            Assert.assertTrue(response.data == null)
            Assert.assertTrue(response.message?.isNotEmpty()!!)
        }
    }

    companion object {
        private const val SUCCESS_CODE = 200
        private const val EXCEPTION_NAME = "Unknown Exception"
    }
}