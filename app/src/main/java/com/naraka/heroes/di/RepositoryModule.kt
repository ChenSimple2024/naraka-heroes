package com.naraka.heroes.di

import com.naraka.heroes.network.NarakaApiService
import com.naraka.heroes.persistence.HeroDao
import com.naraka.heroes.repository.HeroRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideHeroRepository(
        narakaApiService: NarakaApiService,
        heroDao: HeroDao
    ): HeroRepository = HeroRepository(narakaApiService, heroDao)
}
