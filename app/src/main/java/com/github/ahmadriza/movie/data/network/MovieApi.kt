package com.github.ahmadriza.movie.data.network

import com.github.ahmadriza.movie.BuildConfig
import com.github.ahmadriza.movie.models.MovieDetail
import com.github.ahmadriza.movie.models.MovieItem
import com.github.ahmadriza.movie.models.PaginationResponse
import com.github.ahmadriza.movie.models.Review
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("page") page: Int
    ): Response<PaginationResponse<MovieItem>>

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page") page: Int
    ): Response<PaginationResponse<MovieItem>>

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") page: Int
    ): Response<PaginationResponse<MovieItem>>

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("page") page: Int
    ): Response<PaginationResponse<MovieItem>>

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Long
    ): Response<MovieDetail>

    @GET("movie/{id}/reviews")
    suspend fun getMovieReviews(
        @Path("id") id: Long, @Query("page") page: Int
    ): Response<PaginationResponse<Review>>

}