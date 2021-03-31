package com.github.ahmadriza.movie.ui.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.paging.*
import com.github.ahmadriza.movie.data.repository.AppRepository
import com.github.ahmadriza.movie.models.MovieItem
import kotlinx.coroutines.flow.Flow


class MovieFavoriteViewModel @ViewModelInject constructor(private val repository: AppRepository): ViewModel(){


    private val dataSource: DataSource.Factory<Int, MovieItem> = repository.getFavoriteMoviesDataSource()
    val movieDataSource: Flow<PagingData<MovieItem>> = Pager(config = PagingConfig(pageSize = 20)) {
        dataSource.asPagingSourceFactory().invoke()
    }.flow
}