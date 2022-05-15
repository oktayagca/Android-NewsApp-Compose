package com.example.android_newsapp_compose.data.repository

import com.example.android_newsapp_compose.data.remote.RemoteDataSource
import com.example.android_newsapp_compose.data.utils.performNetworkOperation

class Repository(
    private val remoteDataSource: RemoteDataSource,
) {
    fun getTopNews(country:String,key:String) = performNetworkOperation {
        remoteDataSource.getTopNews(country,key)
    }
}