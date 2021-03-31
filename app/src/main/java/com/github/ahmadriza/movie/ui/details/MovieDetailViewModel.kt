package com.github.ahmadriza.movie.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.ahmadriza.movie.data.repository.AppRepository
import com.github.ahmadriza.movie.data.repository.source.MovieReviewsPagingSource
import com.github.ahmadriza.movie.models.MovieDetail
import com.github.ahmadriza.movie.models.MovieItem
import com.github.ahmadriza.movie.models.Review
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieDetailViewModel @ViewModelInject constructor(
    private val repository: AppRepository
): ViewModel(){

    private val _id = MutableLiveData<Long>()
    val isFavorite: LiveData<Boolean> = _id.switchMap { repository.findMovieInFavorite(it) }.map { it != null }

    val detail: Flow<MovieDetail> = _id.asFlow().map {
        repository.getMovieDetail(it)
    }

    val reviews: Flow<PagingData<Review>> =  _id.asFlow().flatMapConcat { id ->
        Pager(PagingConfig(20)){
            MovieReviewsPagingSource {
                repository.getMovieReviews(id, it)
            }
        }.flow
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