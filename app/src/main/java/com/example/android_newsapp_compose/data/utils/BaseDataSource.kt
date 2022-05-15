package com.example.android_newsapp_compose.data.utils

import com.example.android_newsapp_compose.utils.Resource
import retrofit2.Response
import java.util.concurrent.CancellationException

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            val body = response.body()
            return makeError(
                message = response.message(),
                data = body,
                code = response.code().toString()
            )
        } catch (err: Exception) {
            return makeError(
                message = err.message!!,
                data = null,
                code = ""
            )
        } catch (e: CancellationException) {
            return makeError(
                message = e.message!!,
                data = null,
                code = ""
            )
        }
    }

    private fun <T> makeError(message: String, data: T?, code: String?): Resource<T> {
        return Resource.error(message, data, code)
    }
}