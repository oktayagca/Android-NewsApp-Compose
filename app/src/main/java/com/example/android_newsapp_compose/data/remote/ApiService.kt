package com.example.android_newsapp_compose.data.remote

import com.example.android_newsapp_compose.data.entities.TopNewsResponse
import com.example.android_newsapp_compose.data.utils.NetworkConst
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(NetworkConst.GET_TOPS)
    suspend fun getTopNews(@Query("country") country: String,@Query("apiKey") key: String): Response<TopNewsResponse>
}