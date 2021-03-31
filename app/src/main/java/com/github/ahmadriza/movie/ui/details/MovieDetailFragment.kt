package com.github.ahmadriza.movie.ui.details

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.github.ahmadriza.movie.BuildConfig
import com.github.ahmadriza.movie.R
import com.github.ahmadriza.movie.databinding.FragmentMovieDetailsBinding
import com.github.ahmadriza.movie.models.MovieDetail
import com.github.ahmadriza.movie.utils.*
import com.github.ahmadriza.movie.utils.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailsBinding>() {

    private val args: MovieDetailFragmentArgs by navArgs()
    private val viewModel: MovieDetailViewModel by viewModels()

    override fun getLayoutResource(): Int = R.layout.fragment_movie_details

    override fun initViews() {

        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        binding.toolbar.setupWithNavController(findNavController(), appBarConfiguration)
        binding.swipeRefresh.setOnRefreshListener { viewModel.loadData(args.MovieItem.id) }
    }

    override fun initObservers() {
        lifecycleScope.launch {

            viewModel.detail.catch {
                Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_SHORT).show()
                binding.swipeRefresh.isRefreshing = false
            }.onStart {
                binding.swipeRefresh.isRefreshing = true
            }.collectLatest {
                updateUI(it)
                binding.swipeRefresh.isRefreshing = false
            }

        }


        viewModel.isFavorite.observe(viewLifecycleOwner) {
            updateFavState(it)
        }
    }

    override fun initData() {
        binding.toolbar.title = args.MovieItem.title
        viewModel.loadData(args.MovieItem.id)
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(movie: MovieDetail) {
        binding.tvTitle.text = movie.title
        binding.tvYear.text = "${movie.releaseDate.toDateOrNull()?.displayYear} $DOT "
        binding.tvRating.text = "${movie.voteAvg}/10"
        binding.tvOverview.text = movie.overview
        binding.imgThumbnail.loadImage(BuildConfig.IMG_URL_S + movie.posterPath, 8)
        val details = StringBuilder()
        details.append("<b>Genre(s):</b> ")
        movie.genres.forEachIndexed { i, it ->
            details.append("${it.name}")
            if (i < movie.genres.lastIndex) details.append(", ")
        }
        details.append("<br>").append("<b>Language(s):</b> ")
        movie.languages.forEachIndexed { i, it ->
            details.append("${it.name}")
            if (i < movie.languages.lastIndex) details.append(", ")
        }
        details.append("<br>").append("<b>Production(s):</b> ")
        movie.productions.forEachIndexed { i, it ->
            details.append("${it.name}")
            if (i < movie.productions.lastIndex) details.append(", ")
        }
        details.append("<br>").append("<b>Country(s):</b> ")
        movie.countries.forEachIndexed { i, it ->
            details.append("${it.name}")
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