package com.naraka.heroes.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "hero_skills",
    foreignKeys = [ForeignKey(
        entity = Hero::class,
        parentColumns = ["id"],
        childColumns = ["heroId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("heroId")]
)
data class HeroSkill(
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
