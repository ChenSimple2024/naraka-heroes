package com.naraka.heroes.model

import androidx.room.Embedded
import androidx.room.Relation

data class HeroWithSkills(
    @Embedded val hero: Hero,
    @Relation(
        parentColumn = "id",
        entityColumn = "heroId"
    )
    val skills: List<HeroSkill>
)
