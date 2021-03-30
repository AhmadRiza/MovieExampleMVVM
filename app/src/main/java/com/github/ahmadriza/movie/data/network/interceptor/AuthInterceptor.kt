package com.github.ahmadriza.movie.data.network.interceptor

import com.github.ahmadriza.movie.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by on 11/25/20.
 */
class AuthInterceptor @Inject constructor() :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val req = chain.request()
        val url = req.url.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()
        val newReq = req.newBuilder().url(url).build()

        return chain.proceed(newReq)
    }

}