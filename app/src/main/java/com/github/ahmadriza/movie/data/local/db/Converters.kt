package com.github.ahmadriza.movie.data.local.db

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by riza@deliv.co.id on 1/22/20.
 */

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}