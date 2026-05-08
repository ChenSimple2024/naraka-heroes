package com.naraka.heroes.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.naraka.heroes.core.database.dao.HeroDao
import com.naraka.heroes.core.database.entity.HeroEntity
import com.naraka.heroes.core.database.entity.HeroSkillEntity

@Database(
    entities = [HeroEntity::class, HeroSkillEntity::class],
    version = 1,
    exportSchema = true
)
abstract class NarakaDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao
}
