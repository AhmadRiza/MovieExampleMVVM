package com.github.ahmadriza.movie.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by on 11/25/20.
 */

class JsonInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.header("Content-Type", "application/json")
        builder.header("Accept", "application/json")
        return chain.proceed(builder.build())
    }

}