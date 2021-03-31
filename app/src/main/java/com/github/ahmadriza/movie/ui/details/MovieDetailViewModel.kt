package com.github.ahmadriza.movie.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.github.ahmadriza.movie.data.repository.AppRepository
import com.github.ahmadriza.movie.models.MovieDetail
import com.github.ahmadriza.movie.models.MovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieDetailViewModel @ViewModelInject constructor(
    private val repository: AppRepository
): ViewModel(){

    private val _id = MutableLiveData<Long>()
    val isFavorite: LiveData<Boolean> = _id.switchMap { repository.findMovieInFavorite(it) }.map { it != null }

    val detail = _id.asFlow().map {
        repository.getMovieDetail(it)
    }


    fun loadData(movieId: Long){
        _id.value = movieId
    }

    fun addToFavorite(movieItem: MovieItem) = viewModelScope.launch {
        val res = repository.addToFavorite(movieItem)
        Timber.d("added $movieItem -> $res")
    }

    fun deleteFavorite(movieItem: MovieItem) = viewModelScope.launch {
        val res = repository.deleteFavorite(movieItem)
        Timber.d("delete $movieItem -> $res")

    }


}