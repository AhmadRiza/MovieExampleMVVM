package com.github.ahmadriza.movie.data.repository

import com.github.ahmadriza.movie.data.network.MovieService
import com.github.ahmadriza.movie.models.DataResult
import com.github.ahmadriza.movie.models.MovieItem
import com.github.ahmadriza.movie.models.PaginationResponse
import javax.inject.Inject

/**
 * Created by riza@deliv.co.id on 11/25/20.
 */

class AppRepository @Inject constructor(
    private val movieService: MovieService
) {

    suspend fun popularMovie(page: Int) : PaginationResponse<MovieItem> {
        return when(val result = movieService.popularMovies(page)){
            is DataResult.Success -> result.data
            is DataResult.Error -> throw result.error
        }
    }

    suspend fun topRatedMovie(page: Int) : PaginationResponse<MovieItem> {
        return when(val result = movieService.topRated(page)){
            is DataResult.Success -> result.data
            is DataResult.Error -> throw result.error
        }
    }

    suspend fun getNowPlayingMovie(page: Int) : PaginationResponse<MovieItem> {
        return when(val result = movieService.nowPlaying(page)){
            is DataResult.Success -> result.data
            is DataResult.Error -> throw result.error
        }
    }



}

