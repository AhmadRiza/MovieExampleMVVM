package com.github.ahmadriza.movie.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.ahmadriza.movie.models.CryptoEntity

@Dao
interface CryptoDao {

    @Query("SELECT * FROM CryptoEntity ORDER BY lastEdit")
    fun getCrypto(): LiveData<List<CryptoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrypto(crypto: List<CryptoEntity>)

    @Query("DELETE FROM CryptoEntity")
    suspend fun clearAll()

    @Query("UPDATE CryptoEntity SET price = :newPrice WHERE name = :cryptoName")
    suspend fun updatePrice(cryptoName: String, newPrice: Double)
}