package com.naraka.heroes.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val NarakaGold = Color(0xFFC9A84C)
val NarakaDarkRed = Color(0xFF8B1A1A)
val NarakaDarkBg = Color(0xFF1A1A2E)
val NarakaDarkerBg = Color(0xFF0F0F1A)
val NarakaSurface = Color(0xFF16213E)
val NarakaOnSurface = Color(0xFFE8E8E8)

private val DarkColorScheme = darkColorScheme(
    primary = NarakaGold,
    secondary = NarakaDarkRed,
    background = NarakaDarkBg,
    surface = NarakaSurface,
    onPrimary = NarakaDarkerBg,
    onBackground = NarakaOnSurface,
    onSurface = NarakaOnSurface,
)

@Composable
fun NarakaHeroesTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}
