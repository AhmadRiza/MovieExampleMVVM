package com.github.ahmadriza.movie.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


fun <T : ViewDataBinding> ViewGroup.getBindingOf(@LayoutRes layout: Int): T {
    return DataBindingUtil.inflate(
        LayoutInflater.from(context),
        layout,
        this, false
    )
}

