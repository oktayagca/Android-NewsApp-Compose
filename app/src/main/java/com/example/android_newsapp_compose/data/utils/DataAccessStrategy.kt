package com.example.android_newsapp_compose.data.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.android_newsapp_compose.utils.Resource
import kotlinx.coroutines.Dispatchers

fun <T> performNetworkOperation(call: suspend () -> Resource<T>): LiveData<Resource<T>> {
    return liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val networkCall = call.invoke()
        if (networkCall.status == Resource.Status.SUCCESS) {
            val data = networkCall.data!!
            emit(Resource.success(data))
        } else if (networkCall.status == Resource.Status.ERROR) {
            emit(
                Resource.error(
                    networkCall.message!!,
                    networkCall.data,
                    networkCall.code
                )
            )
        }
    }
}