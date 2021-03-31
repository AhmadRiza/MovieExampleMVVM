package com.github.ahmadriza.movie.data.repository

import com.github.ahmadriza.movie.data.local.db.MovieFavoriteDao
import com.github.ahmadriza.movie.data.network.exception.NotFoundException
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AppRepositoryTest {

    private val movieService = Module.movieService
    @Mock
    private lateinit var dao: MovieFavoriteDao

    private lateinit var repository: AppRepository

    @Before
    fun before(){
        repository = AppRepository(movieService, dao)
    }

    @Test
    fun isRepositoryInjected(){
        assertThat(repository).isNotNull()
    }

    @Test
    fun getFirstPagePopularMovieSuccess_notEmpty() = runBlocking{
        val result = repository.popularMovie(1)
        assertThat(result.results).isNotEmpty()
    }

    @Test
    fun changePagePopularMovieSuccess_returnPageSame() = runBlocking{
        val page = 2
        val result = repository.popularMovie(page)
        assertThat(result.page).isEqualTo(page)
    }


    @Test
    fun getFirstPageTopRatedMovieSuccess_notEmpty() = runBlocking{
        val result = repository.topRatedMovie(1)
        assertThat(result.results).isNotEmpty()
    }

    @Test
    fun changePageTopRatedMovieSuccess_returnPageSame() = runBlocking{
        val page = 2
        val result = repository.topRatedMovie(page)
        assertThat(result.page).isEqualTo(page)
    }


    @Test
    fun getFirstPageNowPlayingMovieSuccess_notEmpty() = runBlocking{
        val result = repository.getNowPlayingMovie(1)
        assertThat(result.results).isNotEmpty()
    }

    @Test
    fun changePageNowPlayingMovieSuccess_returnPageSame() = runBlocking{
        val page = 2
        val result = repository.getNowPlayingMovie(page)
        assertThat(result.page).isEqualTo(page)
    }

    @Test
    fun getFirstPageUpcomingMovieSuccess_notEmpty() = runBlocking{
        val result = repository.getUpcomingMovie(1)
        assertThat(result.results).isNotEmpty()
    }

    @Test
    fun changePageUpComingMovieSuccess_returnPageSame() = runBlocking{
        val page = 2
        val result = repository.getUpcomingMovie(page)
        assertThat(result.page).isEqualTo(page)
    }


    @Test
    fun getDetailMovieSuccess_movieDetail() = runBlocking{

        val id = repository.getNowPlayingMovie(1).results.first().id
        val detail = repository.getMovieDetail(id)

        assertThat(detail).isNotNull()

    }

    @Test
    fun getDetailMovieNotFound_NotFoundException() = runBlocking {

        try {
            repository.getMovieDetail(1)
        }catch (e: Exception){
            assertThat(e).isInstanceOf(NotFoundException::class.java)
        }

        return@runBlocking
    }


    @Test
    fun getMovieReviewSuccess_notEmpty() = runBlocking{

        val id = repository.popularMovie(1).results.first().id
        val result = repository.getMovieReviews(id, 1)
        assertThat(result.results).isNotEmpty()

    }




}