package com.example.android_newsapp_compose.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
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
        news =getTopNews("tr","841a49c5f3eb4b88bd6d29c84297894d")
    }
    fun getTopNews(country:String,key:String) = repository.getTopNews(country,key)
}