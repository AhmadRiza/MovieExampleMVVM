package com.github.ahmadriza.movie.ui.favorite

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.github.ahmadriza.movie.R
import com.github.ahmadriza.movie.databinding.FragmentMovieFavoriteBinding
import com.github.ahmadriza.movie.models.MovieItem
import com.github.ahmadriza.movie.ui.home.MovieAdapter
import com.github.ahmadriza.movie.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFavoriteFragment: BaseFragment<FragmentMovieFavoriteBinding>(), MovieAdapter.Listener {

    private val movieAdapter = MovieAdapter(this)
    private val viewModel: MovieFavoriteViewModel by viewModels()

    override fun getLayoutResource(): Int = R.layout.fragment_movie_favorite

    override fun initViews() {

        binding.rvMovies.adapter = movieAdapter
        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        binding.toolbar.setupWithNavController(findNavController(), appBarConfiguration)
    }

    override fun initObservers() {

        lifecycleScope.launch {

            viewModel.movieDataSource.collectLatest {
                movieAdapter.submitData(it)
            }

        }

    }

    override fun initData() {

    }

    override fun onMovieClicked(movie: MovieItem) {
        val action = MovieFavoriteFragmentDirections.actionMovieFavoriteFragmentToMovieDetailFragment(movie)
        findNavController().navigate(action)
    }

}