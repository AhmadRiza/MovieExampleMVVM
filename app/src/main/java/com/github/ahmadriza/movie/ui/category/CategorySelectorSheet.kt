package com.github.ahmadriza.movie.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.github.ahmadriza.movie.R
import com.github.ahmadriza.movie.databinding.SheetCategorySelectorBinding
import com.github.ahmadriza.movie.models.Category
import com.github.ahmadriza.movie.utils.data.autoCleared
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CategorySelectorSheet : BottomSheetDialogFragment(), CategoryAdapter.Listener {


    companion object {

        fun getInstance(
            selectedIndex: Int,
            action: (category: Category, position: Int) -> Unit
        ) = CategorySelectorSheet().apply {
            this.selectedIndex = selectedIndex
            this.onSelectCategory = action
        }

    }

    private var onSelectCategory: ((category: Category, position: Int) -> Unit)? = null

    private var binding: SheetCategorySelectorBinding by autoCleared()
    private val categoryAdapter by lazy { CategoryAdapter(this) }

    private lateinit var categories: List<Category>
    private var selectedIndex: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.sheet_category_selector, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categories = resources.getStringArray(R.array.categories).map { Category(it, false) }
        categories.getOrNull(selectedIndex)?.isSelected = true

        categoryAdapter.submitList(categories)
        binding.rvCategory.adapter = categoryAdapter
        binding.rvCategory.setHasFixedSize(true)

    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog
    }

    override fun onClickCategory(position: Int) {

        if (position != selectedIndex) {
            categories[position].isSelected = true
            categories[selectedIndex].isSelected = false
            categoryAdapter.notifyDataSetChanged()
            onSelectCategory?.invoke(categories[position], position)
        }

        dismiss()
    }


}