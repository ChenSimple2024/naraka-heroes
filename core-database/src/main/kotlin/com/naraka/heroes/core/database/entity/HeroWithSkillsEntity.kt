package com.naraka.heroes.core.database.entity

import com.naraka.heroes.core.model.HeroWithSkills

data class HeroWithSkillsEntity(
    val hero: HeroEntity,
    val skills: List<HeroSkillEntity>
)

fun HeroWithSkillsEntity.asDomain(): HeroWithSkills = HeroWithSkills(
    hero = hero.asDomain(),
    skills = skills.map { it.asDomain() }
)
