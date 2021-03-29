package com.github.ahmadriza.movie.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val service: MainService
) : BaseRemoteDataSource() {

}