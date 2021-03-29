package com.github.ahmadriza.movie.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val id: String = "",
    val name: String,
    val description: String,
    val duration: String,
    val price: String,
    val thumbnail: String,
    val status: String,
    val category: ProductCategory
) : Parcelable {

    fun isActive() = status == "aktif"

}

@Parcelize
data class ProductCategory(val id: String, val name: String, val thumbnail: String) : Parcelable

data class ProductCheckoutRequest(
    @SerializedName("customer_address") val address: String,
    @SerializedName("service_id") val id: String,
    val latitude: String,
    val longitude: String
)





