package com.github.ahmadriza.movie.data.local.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.github.ahmadriza.movie.models.MovieItem
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieFavoriteDao {

    @Query("SELECT * FROM MovieItem ")
    fun getFavoriteMovie(): DataSource.Factory<Int, MovieItem>

    @Query("SELECT * FROM MovieItem WHERE id = :movieId")
    fun getMovie(movieId: Long): LiveData<MovieItem?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(movieItem: MovieItem): Long

    @Delete
    suspend fun deleteFavorite(movieItem: MovieItem): Int


}