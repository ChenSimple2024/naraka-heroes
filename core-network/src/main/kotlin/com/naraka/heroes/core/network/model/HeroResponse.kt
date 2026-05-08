package com.naraka.heroes.core.network.model

import com.naraka.heroes.core.model.Hero
import com.naraka.heroes.core.model.HeroSkill
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeroResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "name_en") val nameEn: String,
    @Json(name = "weapon_type") val weaponType: String,
    @Json(name = "weapon_type_en") val weaponTypeEn: String = "",
    @Json(name = "faction") val faction: String,
    @Json(name = "faction_en") val factionEn: String = "",
    @Json(name = "bio") val bio: String,
    @Json(name = "bio_en") val bioEn: String = "",
    @Json(name = "avatar_url") val avatarUrl: String,
    @Json(name = "portrait_url") val portraitUrl: String,
    @Json(name = "color") val color: String = "#C9A84C",
    @Json(name = "drawable_name") val drawableName: String = "",
    @Json(name = "age") val age: String = "",
    @Json(name = "birthplace") val birthplace: String = "",
    @Json(name = "identity") val identity: String = "",
    @Json(name = "fav_food") val favFood: String = "",
    @Json(name = "dislike_food") val dislikeFood: String = "",
    @Json(name = "hobbies") val hobbies: String = "",
    @Json(name = "dislikes") val dislikes: String = "",
    @Json(name = "cv") val cv: String = "",
    @Json(name = "story") val story: String = "",
    @Json(name = "skills") val skills: List<HeroSkillResponse>
)

@JsonClass(generateAdapter = true)
data class HeroSkillResponse(
    @Json(name = "skill_name") val skillName: String,
    @Json(name = "skill_name_en") val skillNameEn: String = "",
    @Json(name = "skill_description") val skillDescription: String,
    @Json(name = "skill_description_en") val skillDescriptionEn: String = "",
    @Json(name = "skill_icon_url") val skillIconUrl: String,
    @Json(name = "skill_type") val skillType: String,
    @Json(name = "skill_type_en") val skillTypeEn: String = ""
)

fun HeroResponse.asDomain(): Hero = Hero(
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

fun HeroSkillResponse.asDomain(heroId: Long): HeroSkill = HeroSkill(
    heroId = heroId,
    skillName = skillName,
    skillNameEn = skillNameEn,
    skillDescription = skillDescription,
    skillDescriptionEn = skillDescriptionEn,
    skillIconUrl = skillIconUrl,
    skillType = skillType,
    skillTypeEn = skillTypeEn
)
