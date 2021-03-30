package com.github.ahmadriza.movie.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.github.ahmadriza.movie.data.repository.AppRepository
import com.github.ahmadriza.movie.data.repository.source.MoviePagingSource
import com.github.ahmadriza.movie.models.MovieItem
import kotlinx.coroutines.flow.Flow

class MovieListViewModel @ViewModelInject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private var currentCategory: Int = 0

    val movieDataSource: Flow<PagingData<MovieItem>> = Pager(PagingConfig(20)){
        MoviePagingSource{
            when(currentCategory){
                0 -> repository.topRatedMovie(it)
                else-> throw Exception()
            }
        }
    }.flow

    fun setCategory(category: Int) {
        this.currentCategory = category
    }
}