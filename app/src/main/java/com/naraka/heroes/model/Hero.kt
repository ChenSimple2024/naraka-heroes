package com.naraka.heroes.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "heroes")
@Parcelize
data class Hero(
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
    // 角色档案字段
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
