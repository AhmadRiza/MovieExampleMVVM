package com.github.ahmadriza.movie.ui.details

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.github.ahmadriza.movie.R
import com.github.ahmadriza.movie.databinding.ItemReviewsBinding
import com.github.ahmadriza.movie.models.Review
import com.github.ahmadriza.movie.utils.getBindingOf
import com.github.ahmadriza.movie.utils.loadImage
import com.github.ahmadriza.movie.utils.ago
import com.github.ahmadriza.movie.utils.toDateOrNull

class MovieReviewsAdapter: PagingDataAdapter<Review, RecyclerView.ViewHolder>(
    MovieReviewComparator
) {

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val review = getItem(position)!!
        (holder as MovieReviewViewHolder).apply {
            binding.separator.isVisible = position != 0
            binding.ratingBar.rating = review.author.rating.toFloat()
            binding.tvName.text = review.author.name.ifBlank { "Anonymous" }
            binding.tvUsername.text = "@${review.author.username}"
            binding.tvDesc.text = review.content
            binding.tvDate.text = review.updatedAt
                .toDateOrNull(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")?.ago
            review.author.avatar.let {
                if(!it.isNullOrBlank()){
                    binding.imgUser.loadImage(it.removePrefix("/"), makeItCircle = true)
                }
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MovieReviewViewHolder(
            parent.getBindingOf(R.layout.item_reviews)
        )
    }

    class MovieReviewViewHolder(val binding: ItemReviewsBinding) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        private val MovieReviewComparator = object : DiffUtil.ItemCallback<Review>() {
            override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean =
                oldItem == newItem
        }
    }

}