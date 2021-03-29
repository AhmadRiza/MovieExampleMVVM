package com.github.ahmadriza.movie.data.remote

import com.github.ahmadriza.movie.BuildConfig
import com.github.ahmadriza.movie.models.*
import retrofit2.Response
import retrofit2.http.*

interface MainService {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<PaginationResponse<MovieItem>>

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<PaginationResponse<MovieItem>>

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<PaginationResponse<MovieItem>>

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<MovieDetail>

    @GET("movie/{id}/reviews")
    suspend fun getMovieReviews(
        @Path("id") id: Long,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Response<PaginationResponse<Review>>

}