package com.github.ahmadriza.movie.di

import com.github.ahmadriza.movie.BuildConfig
import com.github.ahmadriza.movie.data.network.MovieApi
import com.github.ahmadriza.movie.data.network.MovieService
import com.github.ahmadriza.movie.data.network.interceptor.AuthInterceptor
import com.github.ahmadriza.movie.data.network.interceptor.JsonInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by riza@deliv.co.id on 11/25/20.
 */


@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideJsonInterceptor(): JsonInterceptor = JsonInterceptor()

    @Singleton
    @Provides
    fun provideAuthInterceptor()
            : AuthInterceptor = AuthInterceptor()

    @Provides
    fun provideLogInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        jsonInterceptor: JsonInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addNetworkInterceptor(jsonInterceptor)
        if (BuildConfig.DEBUG) {
            builder
                .addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()


    @Singleton
    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)

    @Singleton
    @Provides
    fun provideMovieService(movieApi: MovieApi): MovieService =
        MovieService(movieApi)



}