package com.github.ahmadriza.movie.ui.home

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.paging.LoadState
import com.github.ahmadriza.movie.R
import com.github.ahmadriza.movie.databinding.FragmentMovieListBinding
import com.github.ahmadriza.movie.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : BaseFragment<FragmentMovieListBinding>() {

    private val viewModel: MovieListViewModel by viewModels()
    private val movieAdapter by lazy { MovieAdapter() }
    override fun getLayoutResource(): Int  = R.layout.fragment_movie_list

    override fun initViews() {

        binding.toolbar.inflateMenu(R.menu.menu_home)
        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        binding.toolbar.setupWithNavController(findNavController(), appBarConfiguration)

        binding.rvMovies.setHasFixedSize(true)
        binding.rvMovies.adapter = movieAdapter.withLoadStateFooter(
            MovieLoadStateAdapter{
                movieAdapter.retry()
            }
        )
        binding.swipeRefresh.setOnRefreshListener { movieAdapter.refresh() }

        movieAdapter.addLoadStateListener {
            binding.swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
        }

    }

    override fun initObservers() {

        lifecycleScope.launch {
            viewModel.movieDataSource.collectLatest {
                movieAdapter.submitData(it)
                Toast.makeText(requireContext(), "updated!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun initData() = Unit

}