package com.github.ahmadriza.movie.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.ahmadriza.movie.data.repository.AppRepository
import com.github.ahmadriza.movie.data.repository.source.MoviePagingSource
import com.github.ahmadriza.movie.models.MovieItem
import com.github.ahmadriza.movie.data.network.exception.UnKnownException
import kotlinx.coroutines.flow.Flow

class MovieListViewModel @ViewModelInject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private var currentCategory: Int = 0

    val movieDataSource: Flow<PagingData<MovieItem>> = Pager(PagingConfig(20)) {
        MoviePagingSource {
            when (currentCategory) {
                0 -> repository.popularMovie(it)
                1 -> repository.getUpcomingMovie(it)
                2 -> repository.topRatedMovie(it)
                3 -> repository.getNowPlayingMovie(it)
                else -> throw UnKnownException()
            }
        }
    }.flow

    fun setCategory(index: Int) {
        this.currentCategory = index
    }

    fun getSelectedCategory() = currentCategory

}