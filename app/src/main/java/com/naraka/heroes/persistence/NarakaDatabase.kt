package com.naraka.heroes.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.naraka.heroes.model.Hero
import com.naraka.heroes.model.HeroSkill

@Database(
    entities = [Hero::class, HeroSkill::class],
    version = 2,
    exportSchema = true
)
abstract class NarakaDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao
}
