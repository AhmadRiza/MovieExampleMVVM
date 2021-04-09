package com.github.ahmadriza.movie.utils

import android.text.format.DateUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created on 12/3/20.
 */

fun String?.toDateOrNull(pattern: String = "yyyy-MM-dd", timezone: String = "GMT"): Date? = try {
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone(timezone)
    sdf.parse(this?: "null")
} catch (e: ParseException) {
    null
}

inline val Date.displayYear: String
    get() {
        val calendar = Calendar.getInstance().apply { time = this@displayYear }
        return calendar.get(Calendar.YEAR).toString()
    }

inline val Date.ago: CharSequence
    get() {
        val now = System.currentTimeMillis()
        return DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
    }