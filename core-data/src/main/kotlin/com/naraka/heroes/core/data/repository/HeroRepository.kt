package com.naraka.heroes.core.data.repository

import androidx.annotation.WorkerThread
import com.naraka.heroes.core.model.Hero
import kotlinx.coroutines.flow.Flow

interface HeroRepository {
    @WorkerThread
    fun fetchHeroList(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<Hero>>

    suspend fun getHeroById(heroId: Long): Hero?

    suspend fun getHeroWithSkills(heroId: Long): com.naraka.heroes.core.model.HeroWithSkills?

    suspend fun updateUserBio(heroId: Long, userBio: String)
}
