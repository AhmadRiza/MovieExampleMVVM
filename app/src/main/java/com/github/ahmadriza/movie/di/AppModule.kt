package com.github.ahmadriza.movie.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.github.ahmadriza.movie.BuildConfig
import com.github.ahmadriza.movie.data.local.LocalDataSource
import com.github.ahmadriza.movie.data.local.SharedPreferenceHelper
import com.github.ahmadriza.movie.data.local.db.AppDB
import com.github.ahmadriza.movie.data.local.db.CryptoDao
import com.github.ahmadriza.movie.data.remote.MainService
import com.github.ahmadriza.movie.data.remote.RemoteDataSource
import com.github.ahmadriza.movie.data.remote.interceptor.AuthInterceptor
import com.github.ahmadriza.movie.data.remote.interceptor.JsonInterceptor
import com.github.ahmadriza.movie.data.repository.MainRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
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
object AppModule {

    // remote

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
    fun provideMainService(retrofit: Retrofit): MainService =
        retrofit.create(MainService::class.java)

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRemoteDataSource(service: MainService): RemoteDataSource = RemoteDataSource(service)

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

    //  Local

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("example-session", 0)

    @Singleton
    @Provides
    fun providePreferenceHelper(
        sharedPreferences: SharedPreferences
    ): SharedPreferenceHelper =
        SharedPreferenceHelper(sharedPreferences)

    @Singleton
    @Provides
    fun provideLocalDataSource(
        helper: SharedPreferenceHelper,
        db: CryptoDao
    ): LocalDataSource =
        LocalDataSource(helper, db)


    // repository

    @Singleton
    @Provides
    fun provideMainRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): MainRepository = MainRepository(localDataSource, remoteDataSource)


    @Singleton
    @Provides
    fun provideDB(@ApplicationContext context: Context): AppDB = Room
        .databaseBuilder(context, AppDB::class.java, "mymovie")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideCryptoDao(appDB: AppDB): CryptoDao = appDB.cryptoDao()

}