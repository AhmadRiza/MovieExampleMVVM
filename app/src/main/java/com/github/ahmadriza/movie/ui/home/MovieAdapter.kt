package com.github.ahmadriza.movie.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.ahmadriza.movie.BuildConfig
import com.github.ahmadriza.movie.R
import com.github.ahmadriza.movie.databinding.ItemMovieBinding
import com.github.ahmadriza.movie.models.MovieItem
import com.github.ahmadriza.movie.utils.*

class MovieAdapter(private val listener: Listener) : PagingDataAdapter<MovieItem, RecyclerView.ViewHolder>(
    MovieModelComparator
) {

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val movie = getItem(position)!!
        (holder as MovieViewHolder).apply {
            binding.tvTitle.text = movie.title
            binding.tvYear.text = movie.releaseDate.toDateOrNull()?.displayYear
            binding.tvRating.text = "${movie.voteAvg}/10"
            binding.tvOverview.text = movie.overview
            binding.tvOverview.setMaxLinesForEllipsizing()
            binding.imgThumbnail.loadImage(BuildConfig.IMG_URL_S+movie.posterPath)
            binding.root.setOnClickListener { listener.onMovieClicked(movie) }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieViewHolder(
            parent.getBindingOf(R.layout.item_movie)
        )
    }

    class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)


    companion object {
        private val MovieModelComparator = object : DiffUtil.ItemCallback<MovieItem>() {
            override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
                oldItem == newItem
        }
    }

    interface Listener {
        fun onMovieClicked(movie: MovieItem)
    }

}