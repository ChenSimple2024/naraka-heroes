package com.naraka.heroes.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.naraka.heroes.core.model.HeroSkill

@Entity(
    tableName = "hero_skills",
    foreignKeys = [ForeignKey(
        entity = HeroEntity::class,
        parentColumns = ["id"],
        childColumns = ["heroId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("heroId")]
)
data class HeroSkillEntity(
    @PrimaryKey(autoGenerate = true) val skillId: Long = 0,
    val heroId: Long,
    val skillName: String,
    val skillNameEn: String = "",
    val skillDescription: String,
    val skillDescriptionEn: String = "",
    val skillIconUrl: String,
    val skillType: String,
    val skillTypeEn: String = ""
)

fun HeroSkillEntity.asDomain(): HeroSkill = HeroSkill(
    skillId = skillId,
    heroId = heroId,
    skillName = skillName,
    skillNameEn = skillNameEn,
    skillDescription = skillDescription,
    skillDescriptionEn = skillDescriptionEn,
    skillIconUrl = skillIconUrl,
    skillType = skillType,
    skillTypeEn = skillTypeEn
)

fun HeroSkill.asEntity(): HeroSkillEntity = HeroSkillEntity(
    skillId = skillId,
    heroId = heroId,
    skillName = skillName,
    skillNameEn = skillNameEn,
    skillDescription = skillDescription,
    skillDescriptionEn = skillDescriptionEn,
    skillIconUrl = skillIconUrl,
    skillType = skillType,
    skillTypeEn = skillTypeEn
)
