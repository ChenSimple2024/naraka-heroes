package com.naraka.heroes.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.naraka.heroes.R
import com.naraka.heroes.model.HeroSkill
import com.naraka.heroes.model.HeroWithSkills
import com.naraka.heroes.ui.theme.NarakaDarkBg
import com.naraka.heroes.ui.theme.NarakaGold
import com.naraka.heroes.ui.theme.NarakaSurface
import com.naraka.heroes.ui.viewmodel.AppLanguage
import com.naraka.heroes.ui.viewmodel.HeroDetailViewModel

// ── Screen 入口（依赖 ViewModel）────────────────────────────────────────────────

@Composable
fun HeroDetailScreen(
    heroId: Long,
    viewModel: HeroDetailViewModel,
    language: AppLanguage,
    onBack: () -> Unit
) {
    val heroWithSkills by viewModel.heroWithSkills.collectAsStateWithLifecycle()
    val isEditDialogVisible by viewModel.isEditDialogVisible.collectAsStateWithLifecycle()
    val editingBio by viewModel.editingBio.collectAsStateWithLifecycle()

    HeroDetailContent(
        heroWithSkills = heroWithSkills,
        isEditDialogVisible = isEditDialogVisible,
        editingBio = editingBio,
        language = language,
        onBack = onBack,
        onShowEditDialog = { viewModel.showEditDialog() },
        onDismissEditDialog = { viewModel.dismissEditDialog() },
        onEditingBioChange = { viewModel.onEditingBioChange(it) },
        onSaveUserBio = { viewModel.saveUserBio() },
        onResetUserBio = { viewModel.resetUserBio() }
    )
}

// ── 纯数据 Composable（可 Preview）──────────────────────────────────────────────

@Composable
fun HeroDetailContent(
    heroWithSkills: HeroWithSkills?,
    isEditDialogVisible: Boolean,
    editingBio: String,
    language: AppLanguage,
    onBack: () -> Unit,
    onShowEditDialog: () -> Unit,
    onDismissEditDialog: () -> Unit,
    onEditingBioChange: (String) -> Unit,
    onSaveUserBio: () -> Unit,
    onResetUserBio: () -> Unit
) {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize().background(NarakaDarkBg)) {
        if (heroWithSkills == null) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = NarakaGold)
        } else {
            val hero = heroWithSkills.hero
            val skills = heroWithSkills.skills
            val heroColor = try {
                Color(android.graphics.Color.parseColor(hero.color))
            } catch (e: Exception) { NarakaGold }

            val displayBio = when {
                hero.userBio.isNotBlank() -> hero.userBio
                language == AppLanguage.ENGLISH && hero.bioEn.isNotBlank() -> hero.bioEn
                else -> hero.bio
            }
            val isUserCustomized = hero.userBio.isNotBlank()

            Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
                // 立绘区域
                Box {
                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(hero.portraitUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = hero.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(420.dp),
                        loading = {
                            Image(
                                painter = painterResource(id = heroDrawableRes(context, hero.drawableName)),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(420.dp)
                            )
                        },
                        error = {
                            Image(
                                painter = painterResource(id = heroDrawableRes(context, hero.drawableName)),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(420.dp)
                            )
                        }
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth().height(180.dp)
                            .align(Alignment.BottomCenter)
                            .background(Brush.verticalGradient(listOf(Color.Transparent, NarakaDarkBg)))
                    )
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.align(Alignment.TopStart).padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = if (language == AppLanguage.CHINESE) "返回" else "Back",
                            tint = Color.White
                        )
                    }
                }

                // 角色信息
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                    Text(
                        text = if (language == AppLanguage.CHINESE) hero.name else hero.nameEn,
                        color = Color.White, fontWeight = FontWeight.Bold, fontSize = 30.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Row {
                        SuggestionChip(
                            onClick = {},
                            label = {
                                Text(
                                    text = if (language == AppLanguage.CHINESE) hero.faction
                                           else hero.factionEn.ifBlank { hero.faction },
                                    fontSize = 12.sp
                                )
                            },
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                containerColor = NarakaGold.copy(alpha = 0.15f), labelColor = NarakaGold
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        SuggestionChip(
                            onClick = {},
                            label = {
                                Text(
                                    text = if (language == AppLanguage.CHINESE) hero.weaponType
                                           else hero.weaponTypeEn.ifBlank { hero.weaponType },
                                    fontSize = 12.sp
                                )
                            },
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                containerColor = Color.White.copy(alpha = 0.08f), labelColor = Color(0xFFCCCCCC)
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    // 简介标题 + 编辑按钮
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.width(3.dp).height(16.dp).background(heroColor))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (language == AppLanguage.CHINESE) "简介" else "Bio",
                            color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp,
                            modifier = Modifier.weight(1f)
                        )
                        if (isUserCustomized) {
                            Text(
                                text = if (language == AppLanguage.CHINESE) "已自定义" else "Custom",
                                color = heroColor, fontSize = 10.sp,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(heroColor.copy(alpha = 0.15f))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                        }
                        IconButton(
                            onClick = onShowEditDialog,
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = if (language == AppLanguage.CHINESE) "编辑简介" else "Edit Bio",
                                tint = NarakaGold,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = displayBio,
                        color = if (isUserCustomized) Color(0xFFDDDDDD) else Color(0xFFBBBBBB),
                        fontSize = 14.sp, lineHeight = 22.sp,
                        fontStyle = if (isUserCustomized) FontStyle.Normal else FontStyle.Italic
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    // 角色档案（年龄、出生地、身份等）
                    if (hero.age.isNotBlank() || hero.birthplace.isNotBlank() || hero.identity.isNotBlank()) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.width(3.dp).height(18.dp).background(heroColor))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (language == AppLanguage.CHINESE) "角色档案" else "Profile",
                                color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        ProfileCard(hero = hero, heroColor = heroColor, language = language)
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                    // 英雄故事
                    if (hero.story.isNotBlank()) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(modifier = Modifier.width(3.dp).height(18.dp).background(heroColor))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (language == AppLanguage.CHINESE) "英雄故事" else "Story",
                                color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = hero.story,
                            color = Color(0xFFBBBBBB), fontSize = 13.sp, lineHeight = 22.sp
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // 技能标题
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.width(3.dp).height(18.dp).background(heroColor))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (language == AppLanguage.CHINESE) "技能" else "Skills",
                            color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    skills.forEach { skill ->
                        SkillItem(skill = skill, heroColor = heroColor, language = language)
                        Spacer(modifier = Modifier.height(4.dp))
                        HorizontalDivider(color = Color.White.copy(alpha = 0.06f))
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }

    // 编辑简介对话框
    if (isEditDialogVisible) {
        val heroColor = try {
            Color(android.graphics.Color.parseColor(heroWithSkills?.hero?.color ?: "#C9A84C"))
        } catch (e: Exception) { NarakaGold }

        AlertDialog(
            onDismissRequest = onDismissEditDialog,
            containerColor = NarakaSurface,
            title = {
                Text(
                    text = if (language == AppLanguage.CHINESE) "编辑简介" else "Edit Bio",
                    color = Color.White, fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column {
                    Text(
                        text = if (language == AppLanguage.CHINESE)
                            "英雄名字和立绘不可修改，仅可自定义简介内容。"
                        else
                            "Hero name and portrait cannot be changed. Only bio is editable.",
                        color = Color(0xFFAAAAAA), fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = editingBio,
                        onValueChange = onEditingBioChange,
                        modifier = Modifier.fillMaxWidth().height(140.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = heroColor,
                            unfocusedBorderColor = Color.White.copy(alpha = 0.3f),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = heroColor
                        ),
                        placeholder = {
                            Text(
                                text = if (language == AppLanguage.CHINESE) "输入自定义简介..." else "Enter custom bio...",
                                color = Color.Gray
                            )
                        },
                        maxLines = 6
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = onSaveUserBio) {
                    Text(
                        text = if (language == AppLanguage.CHINESE) "保存" else "Save",
                        color = heroColor, fontWeight = FontWeight.Bold
                    )
                }
            },
            dismissButton = {
                Row {
                    if (heroWithSkills?.hero?.userBio?.isNotBlank() == true) {
                        TextButton(onClick = onResetUserBio) {
                            Text(
                                text = if (language == AppLanguage.CHINESE) "重置" else "Reset",
                                color = Color(0xFFAAAAAA)
                            )
                        }
                    }
                    TextButton(onClick = onDismissEditDialog) {
                        Text(
                            text = if (language == AppLanguage.CHINESE) "取消" else "Cancel",
                            color = Color(0xFFAAAAAA)
                        )
                    }
                }
            }
        )
    }
}

@Composable
private fun ProfileCard(hero: com.naraka.heroes.model.Hero, heroColor: Color, language: AppLanguage) {
    val isChinese = language == AppLanguage.CHINESE
    val items = buildList {
        if (hero.age.isNotBlank()) add(Pair(if (isChinese) "年龄" else "Age", hero.age))
        if (hero.birthplace.isNotBlank()) add(Pair(if (isChinese) "出生地" else "Birthplace", hero.birthplace))
        if (hero.identity.isNotBlank()) add(Pair(if (isChinese) "身份" else "Identity", hero.identity))
        if (hero.cv.isNotBlank()) add(Pair("CV", hero.cv))
        if (hero.favFood.isNotBlank()) add(Pair(if (isChinese) "喜好食物" else "Fav Food", hero.favFood))
        if (hero.dislikeFood.isNotBlank()) add(Pair(if (isChinese) "讨厌食物" else "Dislike Food", hero.dislikeFood))
        if (hero.hobbies.isNotBlank()) add(Pair(if (isChinese) "爱好" else "Hobbies", hero.hobbies))
        if (hero.dislikes.isNotBlank()) add(Pair(if (isChinese) "讨厌" else "Dislikes", hero.dislikes))
    }
    androidx.compose.foundation.layout.Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White.copy(alpha = 0.04f))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.forEach { (label, value) ->
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = label,
                    color = heroColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.width(80.dp)
                )
                Text(
                    text = value,
                    color = Color(0xFFCCCCCC),
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun SkillItem(skill: HeroSkill, heroColor: Color, language: AppLanguage) {    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(modifier = Modifier.width(4.dp).height(52.dp).background(heroColor.copy(alpha = 0.7f)))
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = if (language == AppLanguage.CHINESE) skill.skillName
                           else skill.skillNameEn.ifBlank { skill.skillName },
                    color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp,
                    modifier = Modifier.weight(1f)
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(3.dp))
                        .background(heroColor.copy(alpha = 0.2f))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = if (language == AppLanguage.CHINESE) skill.skillType
                               else skill.skillTypeEn.ifBlank { skill.skillType },
                        color = heroColor, fontSize = 10.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = if (language == AppLanguage.CHINESE) skill.skillDescription
                       else skill.skillDescriptionEn.ifBlank { skill.skillDescription },
                color = Color(0xFFAAAAAA), fontSize = 13.sp, lineHeight = 20.sp
            )
        }
    }
}
