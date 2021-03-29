package com.github.ahmadriza.movie.models

import com.google.gson.annotations.SerializedName

data class PaginationResponse<T>(
    val page: Int,
    val result: List<T>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)