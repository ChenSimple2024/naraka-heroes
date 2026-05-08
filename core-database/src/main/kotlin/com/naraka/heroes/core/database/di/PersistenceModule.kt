package com.naraka.heroes.core.database.di

import android.app.Application
import androidx.room.Room
import com.naraka.heroes.core.database.NarakaDatabase
import com.naraka.heroes.core.database.dao.HeroDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): NarakaDatabase {
        return Room.databaseBuilder(
            application,
            NarakaDatabase::class.java,
            "Naraka.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideHeroDao(database: NarakaDatabase): HeroDao {
        return database.heroDao()
    }
}
