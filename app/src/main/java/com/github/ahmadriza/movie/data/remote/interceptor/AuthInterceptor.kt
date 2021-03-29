package com.github.ahmadriza.movie.data.remote.interceptor

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
        val builder = req.newBuilder()

        builder.header("Authorization", "Apikey ${BuildConfig.API_KEY}")

        return chain.proceed(builder.build())
    }

}