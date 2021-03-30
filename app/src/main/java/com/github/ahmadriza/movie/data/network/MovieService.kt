package com.github.ahmadriza.movie.data.network

import com.github.ahmadriza.movie.data.base.BaseService
import com.google.gson.Gson
import javax.inject.Inject

class MovieService @Inject constructor(
    private val api: MovieApi
) : BaseService() {

    suspend fun popularMovies(page: Int) = createCall { api.getPopular(page) }
    suspend fun nowPlaying(page: Int) = createCall { api.getNowPlaying(page) }
    suspend fun topRated(page: Int) = createCall { api.getTopRated(page) }
    suspend fun getDetails(id: Long) = createCall { api.getMovieDetails(id) }
    suspend fun getReviews(movieId: Long, page: Int) = createCall { api.getMovieReviews(movieId, page) }


}