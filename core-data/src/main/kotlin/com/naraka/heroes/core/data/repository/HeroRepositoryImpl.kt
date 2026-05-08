package com.naraka.heroes.core.data.repository

import androidx.annotation.WorkerThread
import com.naraka.heroes.core.database.dao.HeroDao
import com.naraka.heroes.core.database.entity.asDomain
import com.naraka.heroes.core.database.entity.asEntity
import com.naraka.heroes.core.model.Hero
import com.naraka.heroes.core.network.model.asDomain
import com.naraka.heroes.core.network.service.NarakaClient
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

internal class HeroRepositoryImpl @Inject constructor(
    private val narakaClient: NarakaClient,
    private val heroDao: HeroDao
) : HeroRepository {

    @WorkerThread
    override fun fetchHeroList(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<Hero>> = flow {
        Timber.d("fetchHeroList: checking database")
        var heroes = heroDao.getHeroList().map { it.asDomain() }
        if (heroes.isEmpty()) {
            Timber.d("fetchHeroList: database empty, fetching from network")
            val response = narakaClient.fetchHeroList()
            response.suspendOnSuccess {
                Timber.d("fetchHeroList: success, items: ${data.size}")
                val domainHeroes = data.map { it.asDomain() }
                val heroEntities = domainHeroes.map { it.asEntity() }
                val skillEntities = data.flatMap { heroResponse ->
                    heroResponse.skills.map { it.asDomain(heroResponse.id).asEntity() }
                }
                heroDao.insertHeroes(heroEntities)
                heroDao.insertHeroSkills(skillEntities)
                emit(domainHeroes)
            }.onFailure {
                Timber.e("fetchHeroList: failure: ${message()}")
                onError(message())
            }
        } else {
            Timber.d("fetchHeroList: database has items: ${heroes.size}")
            emit(heroes)
        }
    }.onStart { onStart() }
     .onCompletion { onComplete() }
     .flowOn(Dispatchers.IO)

    override suspend fun getHeroById(heroId: Long): Hero? {
        return heroDao.getHeroById(heroId)?.asDomain()
    }

    override suspend fun getHeroWithSkills(heroId: Long): com.naraka.heroes.core.model.HeroWithSkills? {
        return heroDao.getHeroWithSkills(heroId)?.asDomain()
    }

    override suspend fun updateUserBio(heroId: Long, userBio: String) {
        heroDao.updateUserBio(heroId, userBio)
    }
}
