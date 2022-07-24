package com.voltek.marvelapp.feature_marvel_heroes.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.voltek.marvelapp.core.extensions.value
import com.voltek.marvelapp.feature_marvel_heroes.presentation.hero_detail.components.HeroDetailScreen
import com.voltek.marvelapp.feature_marvel_heroes.presentation.heroes_list.components.HeroesListScreen
import com.voltek.marvelapp.feature_marvel_heroes.presentation.util.NavigationConstants.DOMINANT_COLOR
import com.voltek.marvelapp.feature_marvel_heroes.presentation.util.NavigationConstants.HERO_DETAIL_SCREEN_ROUTE
import com.voltek.marvelapp.feature_marvel_heroes.presentation.util.NavigationConstants.HEROES_LIST_SCREEN
import com.voltek.marvelapp.feature_marvel_heroes.presentation.util.NavigationConstants.HERO_ID
import com.voltek.marvelapp.ui.theme.MarvelAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalCoilApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            MarvelAppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = HEROES_LIST_SCREEN) {
                    composable(HEROES_LIST_SCREEN) {
                        HeroesListScreen(navController)
                    }

                    composable(
                        HERO_DETAIL_SCREEN_ROUTE,
                        arguments = listOf(
                            navArgument(DOMINANT_COLOR) { type = NavType.IntType },
                            navArgument(HERO_ID) { type = NavType.StringType }
                        )
                    ) {
                        val dominantColor = remember {
                            val color = it.arguments?.getInt(DOMINANT_COLOR)
                            color?.let { Color(it) } ?: Color.White
                        }

                        val characterId = remember {
                            it.arguments?.getString(HERO_ID)
                        }

                        HeroDetailScreen(navController, characterId.value(), dominantColor)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MarvelAppTheme {
        Greeting("Android")
    }
}