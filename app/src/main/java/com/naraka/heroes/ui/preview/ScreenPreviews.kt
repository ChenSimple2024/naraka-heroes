package com.naraka.heroes.ui.preview

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.naraka.heroes.ui.screen.HeroDetailContent
import com.naraka.heroes.ui.screen.HeroListContent
import com.naraka.heroes.ui.theme.NarakaDarkBg
import com.naraka.heroes.ui.theme.NarakaHeroesTheme
import com.naraka.heroes.ui.viewmodel.AppLanguage

// ── HeroListScreen Previews ───────────────────────────────────────────────────

@Preview(name = "HeroList · 中文", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun HeroListPreviewChinese() {
    NarakaHeroesTheme {
        Surface(color = NarakaDarkBg) {
            HeroListContent(
                heroList = previewHeroes,
                isLoading = false,
                errorMessage = null,
                language = AppLanguage.CHINESE,
                onHeroClick = {},
                onToggleLanguage = {}
            )
        }
    }
}

@Preview(name = "HeroList · English", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun HeroListPreviewEnglish() {
    NarakaHeroesTheme {
        Surface(color = NarakaDarkBg) {
            HeroListContent(
                heroList = previewHeroes,
                isLoading = false,
                errorMessage = null,
                language = AppLanguage.ENGLISH,
                onHeroClick = {},
                onToggleLanguage = {}
            )
        }
    }
}

@Preview(name = "HeroList · Loading", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun HeroListPreviewLoading() {
    NarakaHeroesTheme {
        Surface(color = NarakaDarkBg) {
            HeroListContent(
                heroList = emptyList(),
                isLoading = true,
                errorMessage = null,
                language = AppLanguage.CHINESE,
                onHeroClick = {},
                onToggleLanguage = {}
            )
        }
    }
}

// ── HeroDetailScreen Previews ─────────────────────────────────────────────────

@Preview(name = "HeroDetail · 中文", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun HeroDetailPreviewChinese() {
    NarakaHeroesTheme {
        Surface(color = NarakaDarkBg) {
            HeroDetailContent(
                heroWithSkills = previewHeroWithSkills,
                isEditDialogVisible = false,
                editingBio = "",
                language = AppLanguage.CHINESE,
                onBack = {},
                onShowEditDialog = {},
                onDismissEditDialog = {},
                onEditingBioChange = {},
                onSaveUserBio = {},
                onResetUserBio = {}
            )
        }
    }
}

@Preview(name = "HeroDetail · English", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun HeroDetailPreviewEnglish() {
    NarakaHeroesTheme {
        Surface(color = NarakaDarkBg) {
            HeroDetailContent(
                heroWithSkills = previewHeroWithSkills,
                isEditDialogVisible = false,
                editingBio = "",
                language = AppLanguage.ENGLISH,
                onBack = {},
                onShowEditDialog = {},
                onDismissEditDialog = {},
                onEditingBioChange = {},
                onSaveUserBio = {},
                onResetUserBio = {}
            )
        }
    }
}

@Preview(name = "HeroDetail · Edit Dialog", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun HeroDetailPreviewEditDialog() {
    NarakaHeroesTheme {
        Surface(color = NarakaDarkBg) {
            HeroDetailContent(
                heroWithSkills = previewHeroWithSkills,
                isEditDialogVisible = true,
                editingBio = "这是用户自定义的简介内容...",
                language = AppLanguage.CHINESE,
                onBack = {},
                onShowEditDialog = {},
                onDismissEditDialog = {},
                onEditingBioChange = {},
                onSaveUserBio = {},
                onResetUserBio = {}
            )
        }
    }
}
