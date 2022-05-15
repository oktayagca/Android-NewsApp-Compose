package com.example.android_newsapp_compose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.android_newsapp_compose.ui.home.HomeScreen
import com.example.android_newsapp_compose.ui.newsDetail.NewsDetailWebViewScreen
import com.example.android_newsapp_compose.ui.theme.AndroidNewsAppComposeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidNewsAppComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NewsListScreen.route
                    ) {
                        composable(
                            route = Screen.NewsListScreen.route
                        ) {
                            HomeScreen(navController)
                        }
                        composable(
                            route = Screen.NewsDetailScreen.route + "/{newsDetailUrl}",
                            arguments = listOf(navArgument("newsDetailUrl") {
                                type = NavType.StringType
                            })
                        ) { backStackEntry ->
                            NewsDetailWebViewScreen(navController,
                                backStackEntry.arguments?.getString("newsDetailUrl")!!
                            )
                        }

                    }
                }
            }
        }
    }
}
