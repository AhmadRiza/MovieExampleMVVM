package com.github.ahmadriza.movie.ui.category

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.github.ahmadriza.movie.R
import com.github.ahmadriza.movie.databinding.ItemCategoryBinding
import com.github.ahmadriza.movie.models.Category
import com.github.ahmadriza.movie.utils.common.DataBoundListAdapter
import com.github.ahmadriza.movie.utils.getBindingOf
import com.github.ahmadriza.movie.utils.getCompatColor

class CategoryAdapter(private val listener: Listener): DataBoundListAdapter<Category, ItemCategoryBinding>(
    object : DiffUtil.ItemCallback<Category>(){
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

    }
) {

    override fun createBinding(parent: ViewGroup): ItemCategoryBinding {
        return parent.getBindingOf(R.layout.item_category)
    }

    override fun bind(binding: ItemCategoryBinding, item: Category, position: Int) {
        binding.tvCategory.text = item.name
        if(item.isSelected){
            binding.rootView.apply {
                setBackgroundResource(R.drawable.bg_category_active)
            }
            binding.tvCategory.apply {
                setTextColor(context.getCompatColor(R.color.colorPrimary))
            }

        }else{
            binding.rootView.apply {
                setBackgroundResource(R.drawable.bg_category_inactive)
            }
            binding.tvCategory.apply {
                setTextColor(context.getCompatColor(R.color.textSecondary))
            }
        }

        binding.rootView.setOnClickListener { listener.onClickCategory(position) }
    }


    interface Listener{
        fun onClickCategory(position: Int)
    }

}