package com.github.ahmadriza.movie.ui.home

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.paging.LoadState
import com.github.ahmadriza.movie.R
import com.github.ahmadriza.movie.databinding.FragmentMovieListBinding
import com.github.ahmadriza.movie.models.MovieItem
import com.github.ahmadriza.movie.ui.category.CategorySelectorSheet
import com.github.ahmadriza.movie.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : BaseFragment<FragmentMovieListBinding>(), MovieAdapter.Listener {

    private val viewModel: MovieListViewModel by viewModels()
    private val movieAdapter by lazy { MovieAdapter(this) }
    override fun getLayoutResource(): Int = R.layout.fragment_movie_list

    override fun initViews() {
        setupToolBar()
        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        binding.toolbar.setupWithNavController(findNavController(), appBarConfiguration)

        binding.rvMovies.setHasFixedSize(true)
        binding.rvMovies.adapter = movieAdapter.withLoadStateFooter(
            MovieLoadStateAdapter {
                movieAdapter.retry()
            }
        )
        binding.swipeRefresh.setOnRefreshListener { movieAdapter.refresh() }

        movieAdapter.addLoadStateListener {
            binding.swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
            binding.tvError.isVisible = it.refresh is LoadState.Error
        }

        binding.btnCategory.setOnClickListener {
            activity?.run {
                CategorySelectorSheet.getInstance(viewModel.getSelectedCategory()) { category, position ->
                    binding.tvCategory.text = category.name
                    binding.rvMovies.scrollToPosition(0)
                    viewModel.setCategory(position)
                    movieAdapter.refresh()
                }.show(supportFragmentManager, "Category")
            }
        }

    }

    private fun setupToolBar() {

        binding.toolbar.inflateMenu(R.menu.menu_home)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_favorite -> {
                    findNavController().navigate(
                        MovieListFragmentDirections.actionMovieListFragmentToMovieFavoriteFragment()
                    )
                }
            }

            true
        }
    }

    override fun initObservers() {

        lifecycleScope.launch {
            viewModel.movieDataSource.collectLatest {
                movieAdapter.submitData(it)
            }
        }

    }

    override fun initData() {

        val defaultCategory =
            resources.getStringArray(R.array.categories)[viewModel.getSelectedCategory()]
        binding.tvCategory.text = defaultCategory

    }

    override fun onMovieClicked(movie: MovieItem) {
        val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movie)
        findNavController().navigate(action)
    }

}