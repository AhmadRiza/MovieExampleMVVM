package com.github.ahmadriza.movie.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.github.ahmadriza.movie.models.MovieItem

@Dao
interface MovieFavoriteDao {

    @Query("SELECT * FROM MovieItem ")
    fun getFavoriteMovie(): LiveData<List<MovieItem>>

    @Query("SELECT * FROM MovieItem WHERE id = :movieId")
    fun getMovie(movieId: Long): LiveData<MovieItem?>

    @Insert
    fun addFavorite(movieItem: MovieItem): Long

    @Delete
    fun deleteFavorite(movieItem: MovieItem): Int


}