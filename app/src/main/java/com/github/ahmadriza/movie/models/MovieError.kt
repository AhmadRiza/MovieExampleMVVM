package com.github.ahmadriza.movie.models

import com.google.gson.annotations.SerializedName

data class MovieError(
    @SerializedName("status_message") val message: String,
    @SerializedName("status_code") val code: String
)