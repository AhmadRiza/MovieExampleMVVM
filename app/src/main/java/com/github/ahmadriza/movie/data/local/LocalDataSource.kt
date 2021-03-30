package com.github.ahmadriza.movie.data.local

import com.github.ahmadriza.movie.data.local.db.MovieFavoriteDao
import javax.inject.Inject

/**
 * Created by riza@deliv.co.id on 11/25/20.
 */

class LocalDataSource @Inject constructor(
    private val preference: SharedPreferenceHelper,
    private val db: MovieFavoriteDao
    ) : BaseLocalDataSource() {



}