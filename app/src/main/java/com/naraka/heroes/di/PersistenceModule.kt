package com.naraka.heroes.di

import android.content.Context
import androidx.room.Room
import com.naraka.heroes.persistence.HeroDao
import com.naraka.heroes.persistence.NarakaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideNarakaDatabase(@ApplicationContext context: Context): NarakaDatabase =
        Room.databaseBuilder(context, NarakaDatabase::class.java, "naraka_heroes.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideHeroDao(db: NarakaDatabase): HeroDao = db.heroDao()
}
