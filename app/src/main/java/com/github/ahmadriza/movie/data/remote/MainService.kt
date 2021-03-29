package com.github.ahmadriza.movie.data.remote

import com.github.ahmadriza.movie.models.*
import retrofit2.Response
import retrofit2.http.*

interface MainService {

    @GET("data/top/totaltoptiervolfull")
    suspend fun getTopTier24HCrypto(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20,
        @Query("tsym") toSymbol: String = "IDR"
    ): Response<BaseResponse<List<Crypto>>>



}