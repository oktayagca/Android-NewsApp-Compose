package com.example.android_newsapp_compose.utils

data class Resource<out T>(val status: Status, val data: T?, val message: String?,val code: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null,null)
        }

        fun <T> error(message: String, data: T? ,code:String? ): Resource<T> {
            return Resource(Status.ERROR, data, message,code)
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null,null)
        }
    }
}