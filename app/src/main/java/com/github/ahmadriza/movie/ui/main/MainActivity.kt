package com.github.ahmadriza.movie.ui.main

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.github.ahmadriza.movie.R
import com.github.ahmadriza.movie.databinding.ActivityMainBinding
import com.github.ahmadriza.movie.utils.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(){

    private lateinit var navController: NavController

    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun initViews() {

        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

    }

    override fun initObservers() = Unit

    override fun initData() = Unit

}