package com.github.ahmadriza.movie.data.repository

import com.github.ahmadriza.movie.data.local.LocalDataSource
import com.github.ahmadriza.movie.data.remote.RemoteDataSource
import javax.inject.Inject

/**
 * Created by riza@deliv.co.id on 11/25/20.
 */

class MainRepository @Inject constructor(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource
) {

}

