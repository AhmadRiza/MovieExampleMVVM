package com.github.ahmadriza.movie.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class User(
    @SerializedName("user_id") val id: String = "",
    val name: String,
    val address: String,
    @SerializedName("phone_number") val phone: String,
    val balance: String = "0",
    val gender: String,
    val email: String,
    val age: Int?,
)

@Parcelize
data class Therapist(
    val id: String = "",
    val name: String,
    val avatar: String,
    val age: Int,
    val gender: String,
    @SerializedName("phone_number") val phone: String
) : Parcelable

data class LoginRequest(
    val email: String, val password: String
)

data class ForgotPasswordRequest(val email: String)

data class LoginResponse(
    @SerializedName("access_token") val token: String,
    val data: User
)

//register
data class RegisterRequest(
    val name: String,
    val address: String,
    val age: Int,
    val gender: String,
    @SerializedName("phone_number") val phone: String,
    val email: String,
    val password: String,
    @SerializedName("password_confirmation") val passwordConfirm: String = password
)

data class EditUserRequest(
    val name: String,
    val address: String,
    val age: Int,
    val gender: String,
    @SerializedName("phone_number") val phone: String
)
