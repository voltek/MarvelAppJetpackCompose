/*
 * HeroDetailModule.kt
 * Personal App Android
 * Created by Alan Hernández on 25/01/22 21:45
 */

package com.voltek.marvelapp.feature_marvel_heroes.di.hero_detail

import com.voltek.marvelapp.feature_marvel_heroes.domain.repository.MarvelCharacterRepository
import com.voltek.marvelapp.feature_marvel_heroes.domain.use_case.hero_detail.GetHeroDetailUseCase
import com.voltek.marvelapp.feature_marvel_heroes.domain.use_case.images.CalcDominantColorUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
object HeroDetailModule {

    @Provides
    @Singleton
    fun provideGetHeroUseCase(repository: MarvelCharacterRepository): GetHeroDetailUseCase {
        return GetHeroDetailUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideCalculateDominantColorUseCase(): CalcDominantColorUseCase {
        return CalcDominantColorUseCase()
    }
}