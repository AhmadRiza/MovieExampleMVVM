package com.github.ahmadriza.movie.data.repository

import com.github.ahmadriza.movie.di.NetworkModule
import okhttp3.OkHttp
import okhttp3.logging.HttpLoggingInterceptor

object Module {

    private val okHttp = NetworkModule.provideOkHttpClient(NetworkModule.provideAuthInterceptor(), HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.NONE })
    private val movieApi = NetworkModule.provideMovieApi(NetworkModule.provideRetrofit(NetworkModule.provideGson(), okHttp))
    val movieService = NetworkModule.provideMovieService(movieApi)


}