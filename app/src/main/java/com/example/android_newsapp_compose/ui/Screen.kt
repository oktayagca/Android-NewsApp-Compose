package com.example.android_newsapp_compose.ui

sealed class Screen(val route: String) {
    object NewsListScreen : Screen("news_list_screen")
    object NewsDetailScreen : Screen("news_detail_screen")
}