package com.github.ahmadriza.movie.data.remote

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("MetaData") val metadata: Metadata?,
    @SerializedName("Message") val message: String?,
    @SerializedName("Data") val data: T?
) {

    fun isSuccess() = data != null

}

data class Metadata(
    @SerializedName("Count") val count: Int
)