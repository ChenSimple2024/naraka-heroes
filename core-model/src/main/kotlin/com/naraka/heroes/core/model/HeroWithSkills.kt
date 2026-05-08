package com.naraka.heroes.core.model

data class HeroWithSkills(
    val hero: Hero,
    val skills: List<HeroSkill>
)
