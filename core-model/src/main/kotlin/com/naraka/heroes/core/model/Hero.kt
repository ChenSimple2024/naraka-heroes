package com.naraka.heroes.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hero(
    val id: Long,
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
) : Parcelable
