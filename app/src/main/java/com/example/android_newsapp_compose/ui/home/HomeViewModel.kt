package com.example.android_newsapp_compose.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.android_newsapp_compose.BuildConfig
import com.example.android_newsapp_compose.data.entities.TopNewsResponse
import com.example.android_newsapp_compose.data.repository.Repository
import com.example.android_newsapp_compose.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private var repository: Repository,
):ViewModel() {

    lateinit var news : LiveData<Resource<TopNewsResponse>>

    init {
        news =getTopNews("tr",BuildConfig.API_KEY)
    }
    fun getTopNews(country:String,key:String) = repository.getTopNews(country,key)
}