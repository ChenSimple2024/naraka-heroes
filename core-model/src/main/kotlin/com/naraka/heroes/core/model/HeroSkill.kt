package com.naraka.heroes.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeroSkill(
    val skillId: Long = 0,
    val heroId: Long,
    val skillName: String,
    val skillNameEn: String = "",
    val skillDescription: String,
    val skillDescriptionEn: String = "",
    val skillIconUrl: String,
    val skillType: String,
    val skillTypeEn: String = ""
) : Parcelable
