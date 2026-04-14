package com.naraka.heroes.ui.preview

import com.naraka.heroes.model.Hero
import com.naraka.heroes.model.HeroSkill
import com.naraka.heroes.model.HeroWithSkills

/** 所有 Preview 用的假数据，集中管理，不污染业务代码 */

val previewHeroes = listOf(
    Hero(
        id = 1L,
        name = "巫真", nameEn = "Wuzheng",
        weaponType = "弓", weaponTypeEn = "Bow",
        faction = "巫氏", factionEn = "Wu Clan",
        bio = "巫氏之女，身负混沌真气，天资卓绝。历经磨难，守护巫氏，护佑天下。",
        bioEn = "Daughter of the Wu Clan, gifted with Chaos Qi. Through hardship, she protects her clan and the world.",
        avatarUrl = "", portraitUrl = "",
        color = "#4A2E6B", drawableName = "hero_wz_portrait",
        age = "未知", birthplace = "无极帝国-林州天坑",
        identity = "巫氏之女", favFood = "烤肉、白米",
        dislikeFood = "甜食", hobbies = "弓箭、钻研武艺、过年",
        dislikes = "拘束、不敬先祖之人", cv = "陆敏悦",
        story = "昔年羿皇一统天下，召十二部族大巫进京祈福，庇佑无极国祚。巫真便出身于这名门之中，身负混沌真气，天资卓绝。"
    ),
    Hero(
        id = 2L,
        name = "宁红夜", nameEn = "Viper Ning",
        weaponType = "长剑 / 镰刀", weaponTypeEn = "Longsword / Scythe",
        faction = "玉山", factionEn = "Yushan",
        bio = "玉山盲剑客，血液中流淌着致命毒素。她美丽而危险，双刃随时待命。",
        bioEn = "The Blind Blademaster of Yushan, Viper Ning's very blood has long been suffused with lethal poison.",
        avatarUrl = "", portraitUrl = "",
        color = "#8B1A1A", drawableName = "hero_nhy_portrait",
        age = "未知", birthplace = "玉山",
        identity = "玉山盲剑客", cv = "未知",
        story = "玉山盲剑客宁红夜，血液中流淌着致命毒素，以毒为剑，以感知代替视觉，立于人类之巅。"
    )
)

val previewSkills = listOf(
    HeroSkill(
        skillId = 1, heroId = 1,
        skillName = "玉山秘法", skillNameEn = "Yushan Enigma",
        skillDescription = "凝聚气力向前释放，造成击退效果，可打断敌人蓝色蓄力。冷却时间25秒。",
        skillDescriptionEn = "Concentrate Qi and release it forward, knocking back enemies. Cooldown: 25s.",
        skillIconUrl = "", skillType = "F技能", skillTypeEn = "F Skill"
    ),
    HeroSkill(
        skillId = 2, heroId = 1,
        skillName = "暮色赤焰", skillNameEn = "Twilight Crimson",
        skillDescription = "召唤月煞之眼锁定附近敌人，持续5秒并使其眩晕5秒。怒气上限75000。",
        skillDescriptionEn = "Summon the God of Yin's eye to mark and stun nearby enemies. Rage limit: 75000.",
        skillIconUrl = "", skillType = "终极技能", skillTypeEn = "Ultimate"
    )
)

val previewHeroWithSkills = HeroWithSkills(
    hero = previewHeroes[0],
    skills = previewSkills
)
