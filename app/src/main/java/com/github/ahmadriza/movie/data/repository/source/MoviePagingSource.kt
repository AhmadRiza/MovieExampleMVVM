package com.github.ahmadriza.movie.data.repository.source

import androidx.paging.PagingSource
import com.github.ahmadriza.movie.models.MovieItem
import com.github.ahmadriza.movie.models.PaginationResponse


class MoviePagingSource(
    private val networkCall: suspend (page: Int) -> PaginationResponse<MovieItem>
) : PagingSource<Int, MovieItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {

        return try {
            val nextPage = params.key ?: 1
            val movieListResponse = networkCall.invoke(nextPage)

            LoadResult.Page(
                data = movieListResponse.results,
                prevKey = if (nextPage == 1) null else nextPage - 1 ,
                nextKey = if (nextPage < movieListResponse.totalPages)
                    movieListResponse.page.plus(1) else null
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}
