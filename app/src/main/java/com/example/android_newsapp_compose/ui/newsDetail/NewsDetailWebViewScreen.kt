package com.example.android_newsapp_compose.ui.newsDetail

import android.util.Log
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.android_newsapp_compose.R

@Composable
fun NewsDetailWebViewScreen(
    navController: NavController,
    newsDetailUrl: String
) {
    Log.v("url", "article.url")
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.news_detail)) }) },
        content = { LoadWebView(newsDetailUrl = newsDetailUrl) },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = {
                    goBack(navController)
                }
            ){
                Icon(Icons.Filled.ArrowBack,"")
            }
        }
    )

}

@Composable
fun LoadWebView(  newsDetailUrl: String) {
    val context = LocalContext.current
    AndroidView(factory = {
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = object :WebViewClient(){
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    return false
                }
            }
            Log.v("url", newsDetailUrl)
            loadUrl(newsDetailUrl)
        }
    },update = {
        it.loadUrl(newsDetailUrl)
    })
}

fun goBack(navController: NavController){
    navController.popBackStack()
}
