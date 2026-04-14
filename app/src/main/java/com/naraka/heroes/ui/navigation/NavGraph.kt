package com.naraka.heroes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.naraka.heroes.ui.screen.HeroDetailScreen
import com.naraka.heroes.ui.screen.HeroListScreen
import com.naraka.heroes.ui.viewmodel.HeroDetailViewModel
import com.naraka.heroes.ui.viewmodel.HeroListViewModel
import com.naraka.heroes.ui.viewmodel.SettingsViewModel


@Composable
fun NarakaNavGraph(navController: NavHostController) {
    val settingsViewModel: SettingsViewModel = hiltViewModel()
    val language by settingsViewModel.language.collectAsStateWithLifecycle()

    NavHost(navController = navController, startDestination = "hero_list") {
        composable("hero_list") {
            val viewModel: HeroListViewModel = hiltViewModel()
            HeroListScreen(
                viewModel = viewModel,
                language = language,
                onHeroClick = { heroId -> navController.navigate("hero_detail/$heroId") },
                onToggleLanguage = { settingsViewModel.toggleLanguage() }
            )
        }
        composable(
            route = "hero_detail/{heroId}",
            arguments = listOf(navArgument("heroId") { type = NavType.LongType })
        ) {
            val heroId = it.arguments?.getLong("heroId") ?: return@composable
            val viewModel: HeroDetailViewModel = hiltViewModel()
            HeroDetailScreen(
                heroId = heroId,
                viewModel = viewModel,
                language = language,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
