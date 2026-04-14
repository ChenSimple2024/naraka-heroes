package com.naraka.heroes.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.naraka.heroes.model.Hero
import com.naraka.heroes.model.HeroSkill
import com.naraka.heroes.model.HeroWithSkills

@Dao
interface HeroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroList(heroes: List<Hero>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSkillList(skills: List<HeroSkill>)

    @Query("SELECT * FROM heroes")
    fun getHeroList(): List<Hero>

    @Query("SELECT * FROM heroes WHERE id = :heroId")
    fun getHeroById(heroId: Long): Hero

    @Transaction
    @Query("SELECT * FROM heroes WHERE id = :heroId")
    fun getHeroWithSkills(heroId: Long): HeroWithSkills

    @Transaction
    @Query("SELECT * FROM heroes")
    fun getAllHeroesWithSkills(): List<HeroWithSkills>

    @Query("UPDATE heroes SET userBio = :userBio WHERE id = :heroId")
    suspend fun updateUserBio(heroId: Long, userBio: String)
}
