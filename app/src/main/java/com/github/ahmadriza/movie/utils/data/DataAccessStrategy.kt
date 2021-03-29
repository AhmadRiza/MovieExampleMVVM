package com.github.ahmadriza.movie.utils.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.github.ahmadriza.movie.utils.android.RefreshableLiveData
import com.github.ahmadriza.movie.utils.data.Resource.Status.ERROR
import com.github.ahmadriza.movie.utils.data.Resource.Status.SUCCESS
import kotlinx.coroutines.Dispatchers

/**
 * perform API Call in coroutine scope
 */
fun <T> performOperation(
    networkCall: suspend () -> Resource<T>,
    saveCallResult: (suspend (T) -> Unit)? = null,
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())

        val responseStatus = networkCall.invoke()

        if (responseStatus.status == SUCCESS) {
            saveCallResult?.invoke(responseStatus.data!!)
            emit(responseStatus)
        } else if (responseStatus.status == ERROR) {
            emit(Resource.error(responseStatus.message!!))
        }

    }


fun <T> refreshLiveData(networkCall: suspend () -> Resource<T>) = RefreshableLiveData<Resource<T>> {
    it.post(Resource.loading())
    val response = networkCall.invoke()
    if (response.status == SUCCESS) {
        it.post(response)
    } else if (response.status == ERROR) {
        it.post(Resource.error(response.message!!))
    }
}




