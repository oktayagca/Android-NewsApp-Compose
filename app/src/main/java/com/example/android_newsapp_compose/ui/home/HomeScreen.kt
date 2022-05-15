package com.example.android_newsapp_compose.ui.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.android_newsapp_compose.R
import com.example.android_newsapp_compose.data.entities.Article
import com.example.android_newsapp_compose.ui.Screen
import com.example.android_newsapp_compose.ui.items.NewsListItem
import java.net.URLEncoder


@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val news = viewModel.news.observeAsState().value?.data

    if (news != null) {
        Column() {
            CreateToolBar()
            NewsList(newsList = news.articles, navController)
        }
    }
}

@Composable
fun CreateToolBar() {
    TopAppBar(title = { Text(text = stringResource(id = R.string.news_list)) })
}

@Composable
fun NewsList(newsList: List<Article>, navController: NavController) {
    LazyColumn {
        itemsIndexed(items = newsList) { _, item ->
            NewsListItem(news = item,
                onItemClick = { newsDetailUrl ->
                    Log.v("newsDetail",newsDetailUrl )
                    val url = URLEncoder.encode(newsDetailUrl, "utf-8")
                    navController.navigate(Screen.NewsDetailScreen.route + "/${url}")
                }
            )
        }
    }
}
