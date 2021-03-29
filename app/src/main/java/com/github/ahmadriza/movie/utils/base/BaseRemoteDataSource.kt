package com.github.ahmadriza.movie.utils.base

import com.github.ahmadriza.movie.utils.data.Resource
import kotlinx.coroutines.Deferred
import retrofit2.Response
import timber.log.Timber

abstract class BaseRemoteDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Deferred<Response<T>>): Resource<T> {
        try {
            val response = call.invoke().await()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Timber.d(message)
        return Resource.error("Network call has failed for a following reason: $message")
    }

}