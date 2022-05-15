package com.example.android_newsapp_compose.data.remote

import com.example.android_newsapp_compose.data.utils.BaseDataSource

class RemoteDataSource(
    private val apiService: ApiService
) : BaseDataSource(){

    suspend fun getTopNews(country:String,key:String) = getResult {
        apiService.getTopNews(country = country,key = key)
    }
}