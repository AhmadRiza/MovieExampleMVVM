package com.github.ahmadriza.movie.di

import com.github.ahmadriza.movie.data.local.db.MovieFavoriteDao
import com.github.ahmadriza.movie.data.network.MovieService
import com.github.ahmadriza.movie.data.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * Created by riza@deliv.co.id on 11/25/20.
 */


@InstallIn(ApplicationComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideRepository(
        movieService: MovieService,
        movieFavDao: MovieFavoriteDao
    ): AppRepository = AppRepository(movieService, movieFavDao)



}