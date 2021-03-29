package com.github.ahmadriza.movie.data.remote

import com.github.ahmadriza.movie.utils.data.Resource
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import timber.log.Timber

abstract class BaseRemoteDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }

            return error(parseError(response.errorBody()))

        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Timber.d(message)
        return Resource.error(message)
    }

    private fun parseError(body: ResponseBody?): String {

        if (body == null) return "Terjadi kesalahan, mohon coba kembali"

        val json = JSONObject(body.string())

        return try {
            json.getString("message")
        } catch (e: JSONException) {
            "Terjadi kesalahan, mohon coba kembali"
        }

    }

}