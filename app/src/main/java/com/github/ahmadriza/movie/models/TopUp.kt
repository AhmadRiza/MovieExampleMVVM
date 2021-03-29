package com.github.ahmadriza.movie.models

import com.google.gson.annotations.SerializedName


data class Invoice(
    @SerializedName("kode_topup") val code: String,
    val amount: String,
    val description: String,
    @SerializedName("unique_number") val uniqueNumber: Int,
    @SerializedName("final_amount") val finalAmount: String,
    val status: String,
    @SerializedName("expire_at") val expireAt: String,
    @SerializedName("created_at") val date: String
)

data class TopUpRequest(
    val amount: String
)