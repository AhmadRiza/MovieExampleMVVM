package com.github.ahmadriza.movie.utils

import android.content.Context
import com.github.ahmadriza.movie.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created on 12/3/20.
 */

fun countWeekPassedFromDate(date: Date): Int {

    val diff = Date().time - date.time

    val secondInMs: Long = 1000
    val minutesInMs: Long = secondInMs * 60
    val hoursInMs: Long = minutesInMs * 60
    val dayInMs: Long = hoursInMs * 24
    val weekInMs: Long = dayInMs * 7

    return (diff / weekInMs).toInt()
}

fun String.displayDate(context: Context) = toDateOrNull()?.display(context) ?: "Invalid Date"

fun String.toDateOrNull(
    format: String = "yyyy-MM-dd HH:mm:ss",
    isUtc: Boolean = true
): Date? {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    if (isUtc) sdf.timeZone = TimeZone.getTimeZone("UTC")

    return try {
        sdf.parse(this)
    } catch (e: ParseException) {
        null
    }
}

fun Date.toTimeStamp(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return sdf.format(this)
}

fun Int.printDoubleDigits() = String.format("%02d", this)

fun Date.display(context: Context): String {

    val monthsList: Array<String> = context.resources.getStringArray(R.array.bulan)
//    val arrHari: Array<String> = context.resources.getStringArray(R.array.hari)

    val cal = Calendar.getInstance().apply { time = this@display }

//    val day = arrHari[cal.get(Calendar.DAY_OF_WEEK) - 1]
    val date = cal.get(Calendar.DATE)
    val month = monthsList[cal.get(Calendar.MONTH)]
    val year = cal.get(Calendar.YEAR)
    val hour = cal.get(Calendar.HOUR_OF_DAY).printDoubleDigits()
    val minute = cal.get(Calendar.MINUTE).printDoubleDigits()

    return "$date $month $year, $hour:$minute"

}