package com.naraka.heroes.repository

import androidx.annotation.WorkerThread
import com.naraka.heroes.model.Hero
import com.naraka.heroes.model.HeroWithSkills
import com.naraka.heroes.model.toHero
import com.naraka.heroes.model.toHeroSkill
import com.naraka.heroes.network.NarakaApiService
import com.naraka.heroes.persistence.HeroDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HeroRepository @Inject constructor(
    private val narakaApiService: NarakaApiService,
    private val heroDao: HeroDao
) {
    @WorkerThread
    fun loadHeroes(onError: (String?) -> Unit): Flow<List<Hero>> = flow {
        val cached = heroDao.getHeroList()
        if (cached.isEmpty()) {
            try {
                val responses = narakaApiService.fetchHeroes()
                val heroes = responses.map { it.toHero() }
                val skills = responses.flatMap { r -> r.skills.map { it.toHeroSkill(r.id) } }
                heroDao.insertHeroList(heroes)
                heroDao.insertSkillList(skills)
                emit(heroes)
            } catch (e: Exception) {
                onError(e.message)
                emit(emptyList()) // 确保 Flow 总是 emit，让 isLoading 能结束
            }
        } else {
            emit(cached)
        }
    }.flowOn(Dispatchers.IO)

    fun getHeroWithSkills(heroId: Long): Flow<HeroWithSkills> = flow {
        emit(heroDao.getHeroWithSkills(heroId))
    }.flowOn(Dispatchers.IO)

    suspend fun updateUserBio(heroId: Long, userBio: String) {
        heroDao.updateUserBio(heroId, userBio)
    }
}
