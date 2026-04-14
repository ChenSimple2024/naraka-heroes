package com.naraka.heroes.model

import com.google.gson.annotations.SerializedName

data class HeroResponse(
    val id: Long,
    val name: String,
    @SerializedName("name_en") val nameEn: String,
    @SerializedName("weapon_type") val weaponType: String,
    @SerializedName("weapon_type_en") val weaponTypeEn: String = "",
    val faction: String,
    @SerializedName("faction_en") val factionEn: String = "",
    val bio: String,
    @SerializedName("bio_en") val bioEn: String = "",
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("portrait_url") val portraitUrl: String,
    val color: String = "#C9A84C",
    @SerializedName("drawable_name") val drawableName: String = "",
    // 角色档案
    val age: String = "",
    val birthplace: String = "",
    val identity: String = "",
    @SerializedName("fav_food") val favFood: String = "",
    @SerializedName("dislike_food") val dislikeFood: String = "",
    val hobbies: String = "",
    val dislikes: String = "",
    val cv: String = "",
    val story: String = "",
    val skills: List<HeroSkillResponse>
)

data class HeroSkillResponse(
    @SerializedName("skill_name") val skillName: String,
    @SerializedName("skill_name_en") val skillNameEn: String = "",
    @SerializedName("skill_description") val skillDescription: String,
    @SerializedName("skill_description_en") val skillDescriptionEn: String = "",
    @SerializedName("skill_icon_url") val skillIconUrl: String,
    @SerializedName("skill_type") val skillType: String,
    @SerializedName("skill_type_en") val skillTypeEn: String = ""
)

fun HeroResponse.toHero(): Hero = Hero(
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

fun HeroSkillResponse.toHeroSkill(heroId: Long): HeroSkill = HeroSkill(
    heroId = heroId,
    skillName = skillName,
    skillNameEn = skillNameEn,
    skillDescription = skillDescription,
    skillDescriptionEn = skillDescriptionEn,
    skillIconUrl = skillIconUrl,
    skillType = skillType,
    skillTypeEn = skillTypeEn
)
