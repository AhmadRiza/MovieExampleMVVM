package com.github.ahmadriza.movie.ui.home

import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.github.ahmadriza.movie.R
import com.github.ahmadriza.movie.databinding.FragmentMovieListBinding
import com.github.ahmadriza.movie.utils.base.BaseFragment

class MovieListFragment : BaseFragment<FragmentMovieListBinding>() {

    override fun getLayoutResource(): Int  = R.layout.fragment_movie_list

    override fun initViews() {

        binding.toolbar.inflateMenu(R.menu.menu_home)
        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        binding.toolbar.setupWithNavController(findNavController(), appBarConfiguration)

    }

    override fun initObservers() {

    }

    override fun initData() = Unit

}