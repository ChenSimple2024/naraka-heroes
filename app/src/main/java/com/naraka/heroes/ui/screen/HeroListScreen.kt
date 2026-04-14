package com.naraka.heroes.ui.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.naraka.heroes.R
import com.naraka.heroes.model.Hero
import com.naraka.heroes.ui.theme.NarakaDarkBg
import com.naraka.heroes.ui.theme.NarakaGold
import com.naraka.heroes.ui.theme.NarakaSurface
import com.naraka.heroes.ui.viewmodel.AppLanguage
import com.naraka.heroes.ui.viewmodel.HeroListViewModel
import kotlin.math.absoluteValue

/** 根据 Hero.drawableName 动态查找本地 drawable 资源 ID */
fun heroDrawableRes(context: android.content.Context, drawableName: String): Int {
    if (drawableName.isBlank()) return R.drawable.naraka_logo
    val resId = context.resources.getIdentifier(drawableName, "drawable", context.packageName)
    return if (resId != 0) resId else R.drawable.naraka_logo
}

// ── Screen 入口（依赖 ViewModel）────────────────────────────────────────────────

@Composable
fun HeroListScreen(
    viewModel: HeroListViewModel,
    language: AppLanguage,
    onHeroClick: (Long) -> Unit,
    onToggleLanguage: () -> Unit
) {
    val heroList by viewModel.heroList.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val errorMessage by viewModel.errorMessage.collectAsStateWithLifecycle()

    HeroListContent(
        heroList = heroList,
        isLoading = isLoading,
        errorMessage = errorMessage,
        language = language,
        onHeroClick = onHeroClick,
        onToggleLanguage = onToggleLanguage
    )
}

// ── 纯数据 Composable（可 Preview）──────────────────────────────────────────────

@Composable
fun HeroListContent(
    heroList: List<Hero>,
    isLoading: Boolean,
    errorMessage: String?,
    language: AppLanguage,
    onHeroClick: (Long) -> Unit,
    onToggleLanguage: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NarakaDarkBg)
    ) {
        when {
            isLoading -> CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center), color = NarakaGold
            )

            errorMessage != null -> Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = errorMessage, color = Color.White)
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = NarakaGold)
                ) {
                    Text(
                        if (language == AppLanguage.CHINESE) "重试" else "Retry",
                        color = Color.Black
                    )
                }
            }

            heroList.isNotEmpty() -> HeroPagerContent(
                heroList = heroList,
                language = language,
                onHeroClick = onHeroClick,
                onToggleLanguage = onToggleLanguage
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun HeroPagerContent(
    heroList: List<Hero>,
    language: AppLanguage,
    onHeroClick: (Long) -> Unit,
    onToggleLanguage: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { heroList.size })
    val currentHero = heroList.getOrNull(pagerState.currentPage)

    val bgColor by animateColorAsState(
        targetValue = try {
            Color(
                android.graphics.Color.parseColor(
                    currentHero?.color ?: "#1A1A2E"
                )
            ).copy(alpha = 0.35f)
        } catch (e: Exception) {
            NarakaSurface.copy(alpha = 0.35f)
        },
        animationSpec = tween(550), label = "bgColor"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(listOf(NarakaDarkBg, bgColor, NarakaDarkBg))
                )
        )

        Column(modifier = Modifier.fillMaxSize()) {
            // 顶부栏：logo + 语言切换
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)
            ) {
                // 官方中文 logo（透明背景 PNG）
                Image(
                    painter = painterResource(id = R.drawable.naraka_logo_cn),
                    contentDescription = "永劫无间",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(52.dp)
                        .align(Alignment.Center)
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clip(RoundedCornerShape(20.dp))
                        .background(NarakaGold.copy(alpha = 0.15f))
                        .border(1.dp, NarakaGold.copy(alpha = 0.3f), RoundedCornerShape(20.dp))
                        .clickable(onClick = onToggleLanguage)
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = if (language == AppLanguage.CHINESE) "EN" else "中文",
                        color = NarakaGold, fontSize = 12.sp, fontWeight = FontWeight.Bold
                    )
                }
            }

            Text(
                text = if (language == AppLanguage.CHINESE) "选择你的英雄" else "Choose Your Hero",
                color = Color.White.copy(alpha = 0.7f), fontSize = 13.sp, letterSpacing = 2.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            // HorizontalPager
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 48.dp),
                pageSpacing = 16.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { page ->
                val hero = heroList[page]
                val pageOffset =
                    (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
                val scale by animateFloatAsState(
                    targetValue = if (pageOffset.absoluteValue < 0.5f) 1f - pageOffset.absoluteValue * 0.25f else 0.82f,
                    animationSpec = tween(150), label = "scale"
                )
                val alpha by animateFloatAsState(
                    targetValue = if (pageOffset.absoluteValue < 0.5f) 1f else 0.55f,
                    animationSpec = tween(150), label = "alpha"
                )
                HeroCard(
                    hero = hero,
                    modifier = Modifier.graphicsLayer {
                        scaleX = scale; scaleY = scale; this.alpha = alpha
                    },
                    onClick = { onHeroClick(hero.id) }
                )
            }

            // 立绘下方：角色信息区域（优化间距）
            currentHero?.let { hero ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp)
                        .padding(top = 16.dp, bottom = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // 英雄名
                    Text(
                        text = if (language == AppLanguage.CHINESE) hero.name else hero.nameEn,
                        color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    // 阵营 + 武器标签
                    Row(horizontalArrangement = Arrangement.Center) {
                        TagChip(
                            text = if (language == AppLanguage.CHINESE) hero.faction
                            else hero.factionEn.ifBlank { hero.faction }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        TagChip(
                            text = if (language == AppLanguage.CHINESE) hero.weaponType
                            else hero.weaponTypeEn.ifBlank { hero.weaponType }
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    // 简介
                    Text(
                        text = when {
                            hero.userBio.isNotBlank() -> hero.userBio
                            language == AppLanguage.ENGLISH && hero.bioEn.isNotBlank() -> hero.bioEn
                            else -> hero.bio
                        },
                        color = Color(0xFFAAAAAA), fontSize = 12.sp, lineHeight = 20.sp,
                        textAlign = TextAlign.Center, minLines = 3, maxLines = 3,
                        overflow = TextOverflow.Ellipsis, fontStyle = FontStyle.Italic
                    )
                }
            }

            // 页面指示点
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 24.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(heroList.size) { index ->
                    val isSelected = pagerState.currentPage == index
                    val dotColor by animateColorAsState(
                        targetValue = if (isSelected) NarakaGold else Color.White.copy(alpha = 0.3f),
                        animationSpec = tween(300), label = "dot"
                    )
                    val dotSize by animateFloatAsState(
                        targetValue = if (isSelected) 8f else 5f,
                        animationSpec = tween(300), label = "dotSize"
                    )
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 3.dp)
                            .size(dotSize.dp)
                            .clip(CircleShape)
                            .background(dotColor)
                    )
                }
            }
        }
    }
}

@Composable
private fun HeroCard(hero: Hero, modifier: Modifier = Modifier, onClick: () -> Unit) {
    val context = LocalContext.current
    val heroColor = try {
        Color(android.graphics.Color.parseColor(hero.color))
    } catch (e: Exception) {
        NarakaGold
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                16.dp, RoundedCornerShape(20.dp),
                ambientColor = heroColor.copy(alpha = 0.4f),
                spotColor = heroColor.copy(alpha = 0.4f)
            )
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = NarakaSurface)
    ) {
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
                    .height(380.dp),
                loading = {
                    Image(
                        painter = painterResource(id = heroDrawableRes(context, hero.drawableName)),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(380.dp)
                    )
                },
                error = {
                    Image(
                        painter = painterResource(id = heroDrawableRes(context, hero.drawableName)),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(380.dp)
                    )
                }
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, NarakaSurface.copy(alpha = 0.85f), NarakaSurface)
                        )
                    )
            )
        }
    }
}

@Composable
private fun TagChip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(NarakaGold.copy(alpha = 0.15f))
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(text = text, color = NarakaGold, fontSize = 11.sp)
    }
}
