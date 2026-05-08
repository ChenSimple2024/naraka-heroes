package com.naraka.heroes.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.naraka.heroes.core.model.Hero

@Entity(tableName = "heroes")
data class HeroEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val nameEn: String,
    val weaponType: String,
    val weaponTypeEn: String = "",
    val faction: String,
    val factionEn: String = "",
    val bio: String,
    val bioEn: String = "",
    val avatarUrl: String,
    val portraitUrl: String,
    val color: String = "#C9A84C",
    val drawableName: String = "",
    val userBio: String = "",
    val age: String = "",
    val birthplace: String = "",
    val identity: String = "",
    val favFood: String = "",
    val dislikeFood: String = "",
    val hobbies: String = "",
    val dislikes: String = "",
    val cv: String = "",
    val story: String = ""
)

fun HeroEntity.asDomain(): Hero = Hero(
    id = id,
    name = name,
    nameEn = nameEn,
    weaponType = weaponType,
    weaponTypeEn = weaponTypeEn,
    faction = faction,
    factionEn = factionEn,
    bio = bio,
    bioEn = bioEn,
    avatarUrl = avatarUrl,
    portraitUrl = portraitUrl,
    color = color,
    drawableName = drawableName,
    userBio = userBio,
    age = age,
    birthplace = birthplace,
    identity = identity,
    favFood = favFood,
    dislikeFood = dislikeFood,
    hobbies = hobbies,
    dislikes = dislikes,
    cv = cv,
    story = story
)

fun Hero.asEntity(): HeroEntity = HeroEntity(
    id = id,
    name = name,
    nameEn = nameEn,
    weaponType = weaponType,
    weaponTypeEn = weaponTypeEn,
    faction = faction,
    factionEn = factionEn,
    bio = bio,
    bioEn = bioEn,
    avatarUrl = avatarUrl,
    portraitUrl = portraitUrl,
    color = color,
    drawableName = drawableName,
    userBio = userBio,
    age = age,
    birthplace = birthplace,
    identity = identity,
    favFood = favFood,
    dislikeFood = dislikeFood,
    hobbies = hobbies,
    dislikes = dislikes,
    cv = cv,
    story = story
)
