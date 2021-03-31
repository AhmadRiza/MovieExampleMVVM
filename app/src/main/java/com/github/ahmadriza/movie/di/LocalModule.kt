package com.github.ahmadriza.movie.di

import android.content.Context
import androidx.room.Room
import com.github.ahmadriza.movie.data.local.db.AppDB
import com.github.ahmadriza.movie.data.local.db.MovieFavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by riza@deliv.co.id on 11/25/20.
 */


@InstallIn(ApplicationComponent::class)
@Module
object LocalModule {

    @Singleton
    @Provides
    fun provideDB(@ApplicationContext context: Context): AppDB = Room
        .databaseBuilder(context, AppDB::class.java, "mymovie")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideCryptoDao(appDB: AppDB): MovieFavoriteDao = appDB.favoriteDao()

}