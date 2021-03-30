package com.github.ahmadriza.movie.utils

import android.content.Context
import android.view.View
import com.github.ahmadriza.movie.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created on 12/3/20.
 */

fun String.toDateOrNull(): Date? = try {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    sdf.parse(this)
} catch (e: ParseException) {
    null
}

inline val Date.displayYear: String
    get() {
        val calendar  = Calendar.getInstance().apply { time = this@displayYear }
        return calendar.get(Calendar.YEAR).toString()
    }
