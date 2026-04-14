package com.naraka.heroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.naraka.heroes.ui.navigation.NarakaNavGraph
import com.naraka.heroes.ui.theme.NarakaHeroesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NarakaHeroesTheme {
                val navController = rememberNavController()
                NarakaNavGraph(navController = navController)
            }
        }
    }
}
