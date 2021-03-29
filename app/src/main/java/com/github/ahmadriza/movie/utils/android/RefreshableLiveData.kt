package com.github.ahmadriza.movie.utils.android

import androidx.lifecycle.MutableLiveData

class RefreshableLiveData<T>(private val refreshCallback: suspend (source: DataCallback<T>) -> Unit) :
    MutableLiveData<T>(), DataCallback<T> {

    suspend fun refresh() {
        refreshCallback(this)
    }

    override fun post(data: T) {
        postValue(data)
    }

}

interface DataCallback<T> {
    fun post(data: T)
}