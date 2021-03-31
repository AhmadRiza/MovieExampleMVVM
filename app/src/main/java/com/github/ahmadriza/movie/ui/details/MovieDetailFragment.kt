package com.github.ahmadriza.movie.ui.details

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.paging.LoadState
import androidx.paging.LoadStates
import com.github.ahmadriza.movie.BuildConfig
import com.github.ahmadriza.movie.R
import com.github.ahmadriza.movie.databinding.FragmentMovieDetailsBinding
import com.github.ahmadriza.movie.models.MovieDetail
import com.github.ahmadriza.movie.ui.home.MovieLoadStateAdapter
import com.github.ahmadriza.movie.utils.*
import com.github.ahmadriza.movie.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailsBinding>() {

    private val args: MovieDetailFragmentArgs by navArgs()
    private val viewModel: MovieDetailViewModel by viewModels()
    private val reviewsAdapter by lazy { MovieReviewsAdapter() }

    override fun getLayoutResource(): Int = R.layout.fragment_movie_details

    override fun initViews() {

        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        binding.toolbar.setupWithNavController(findNavController(), appBarConfiguration)
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadData(args.MovieItem.id)
            reviewsAdapter.refresh()
        }

        binding.rvReviews.adapter = reviewsAdapter.withLoadStateFooter(MovieLoadStateAdapter {
            reviewsAdapter.retry()
        })

        reviewsAdapter.addLoadStateListener {
            binding.loadingReviews.isVisible = it.refresh is LoadState.Loading
            binding.emptyMessage.isVisible = (it.refresh is LoadState.NotLoading
                    && reviewsAdapter.itemCount == 0)
            binding.errorMessage.isVisible = it.refresh is LoadState.Error
        }

    }

    override fun initObservers() {
        lifecycleScope.launch {

            viewModel.detail.catch {
                Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
                binding.swipeRefresh.isRefreshing = false
            }
                .onStart { binding.swipeRefresh.isRefreshing = true }
                .collectLatest {
                    updateUI(it)
                    binding.swipeRefresh.isRefreshing = false
                }

        }

        lifecycleScope.launch {
            viewModel.reviews.collectLatest {
                reviewsAdapter.submitData(it)
            }
        }


        viewModel.isFavorite.observe(viewLifecycleOwner) {
            updateFavState(it)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun initData() {
        binding.toolbar.title = args.MovieItem.title
        viewModel.loadData(args.MovieItem.id)
        args.MovieItem.let { movie ->
            binding.tvTitle.text = movie.title
            binding.tvYear.text = "${movie.releaseDate.toDateOrNull()?.displayYear} $DOT "
            binding.tvRating.text = "${movie.voteAvg}/10"
            binding.tvOverview.text = movie.overview
            binding.imgThumbnail.loadImage(BuildConfig.IMG_URL_S + movie.posterPath, 8.px)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(movie: MovieDetail) {
        val details = StringBuilder()
        details.append("<b>Genre(s):</b> ")
        movie.genres.forEachIndexed { i, it ->
            details.append(it.name)
            if (i < movie.genres.lastIndex) details.append(", ")
        }
        details.append("<br>").append("<b>Language(s):</b> ")
        movie.languages.forEachIndexed { i, it ->
            details.append(it.name)
            if (i < movie.languages.lastIndex) details.append(", ")
        }
        details.append("<br>").append("<b>Production(s):</b> ")
        movie.productions.forEachIndexed { i, it ->
            details.append(it.name)
            if (i < movie.productions.lastIndex) details.append(", ")
        }
        details.append("<br>").append("<b>Country(s):</b> ")
        movie.countries.forEachIndexed { i, it ->
            details.append(it.name)
            if (i < movie.countries.lastIndex) details.append(", ")
        }
        binding.tvDetails.loadHTML(details.toString())


    }

    private fun updateFavState(isFav: Boolean) = binding.btnFavorite.apply {
        if (isFav) {
            setBackgroundResource(R.drawable.bg_button_accent_invert)
            setTextColor(context.getCompatColor(R.color.colorAccent))
            setDrawableTint(R.color.colorAccent)
            text = "Remove from Favorite"
            setOnClickListener {
                viewModel.deleteFavorite(args.MovieItem)
            }
        } else {
            setBackgroundResource(R.drawable.bg_button_accent)
            setTextColor(context.getCompatColor(R.color.white))
            setDrawableTint(R.color.white)
            text = "Add to Favorite"
            setOnClickListener {
                viewModel.addToFavorite(args.MovieItem)
            }
        }
    }
}