package com.naraka.heroes.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.naraka.heroes.core.database.entity.HeroEntity
import com.naraka.heroes.core.database.entity.HeroSkillEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HeroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroes(heroes: List<HeroEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroSkills(skills: List<HeroSkillEntity>)

    @Query("SELECT * FROM heroes")
    suspend fun getHeroList(): List<HeroEntity>

    @Query("SELECT * FROM heroes")
    fun getAllHeroes(): Flow<List<HeroEntity>>

    @Query("SELECT * FROM heroes WHERE id = :heroId")
    suspend fun getHeroById(heroId: Long): HeroEntity?

    @Query("SELECT * FROM hero_skills WHERE heroId = :heroId")
    suspend fun getSkillsByHeroId(heroId: Long): List<HeroSkillEntity>

    @androidx.room.Transaction
    suspend fun getHeroWithSkills(heroId: Long): com.naraka.heroes.core.database.entity.HeroWithSkillsEntity? {
        val hero = getHeroById(heroId) ?: return null
        val skills = getSkillsByHeroId(heroId)
        return com.naraka.heroes.core.database.entity.HeroWithSkillsEntity(hero, skills)
    }

    @Query("UPDATE heroes SET userBio = :userBio WHERE id = :heroId")
    suspend fun updateUserBio(heroId: Long, userBio: String)
}
