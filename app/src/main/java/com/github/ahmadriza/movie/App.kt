package com.github.ahmadriza.movie

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by riza@deliv.co.id on 11/25/20.
 */

@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }


    }
}