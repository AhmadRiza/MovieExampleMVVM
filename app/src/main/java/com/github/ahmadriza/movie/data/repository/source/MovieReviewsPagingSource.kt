package com.github.ahmadriza.movie.data.repository.source

import androidx.paging.PagingSource
import com.github.ahmadriza.movie.models.PaginationResponse
import com.github.ahmadriza.movie.models.Review


class MovieReviewsPagingSource(
    private val networkCall: suspend (page: Int) -> PaginationResponse<Review>
) : PagingSource<Int, Review>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {

        return try {
            val nextPage = params.key ?: 1
            val reviewListResponse = networkCall.invoke(nextPage)

            LoadResult.Page(
                data = reviewListResponse.results,
                prevKey = if (nextPage == 1) null else nextPage - 1 ,
                nextKey = if (nextPage < reviewListResponse.totalPages)
                    reviewListResponse.page.plus(1) else null
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}
