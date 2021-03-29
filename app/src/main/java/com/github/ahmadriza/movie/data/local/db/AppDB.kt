package com.github.ahmadriza.movie.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.ahmadriza.movie.models.CryptoEntity

@TypeConverters(Converters::class)
@Database(entities = [CryptoEntity::class], version = 1, exportSchema = false)
abstract class AppDB: RoomDatabase() {

    abstract fun cryptoDao(): CryptoDao

}