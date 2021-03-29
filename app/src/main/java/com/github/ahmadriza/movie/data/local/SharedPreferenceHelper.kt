package com.github.ahmadriza.movie.data.local

import android.content.SharedPreferences
import javax.inject.Inject

/**
 * Created by riza@deliv.co.id on 11/25/20.
 */
class SharedPreferenceHelper @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {


    fun saveUser(username: String) {
        sharedPreferences.edit().putString("username", username).apply()
    }

    fun getUserName() = sharedPreferences.getString("username", null)

    fun logOut() = sharedPreferences.edit().clear().apply()

}