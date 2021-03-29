package com.github.ahmadriza.movie.utils.android

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

class VisibleItemListener(private val lm: LinearLayoutManager, private val onStopScroll:(start: Int, end: Int)->Unit): RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val first = lm.findFirstCompletelyVisibleItemPosition()
        val last = lm.findLastCompletelyVisibleItemPosition()
        Timber.d("$first...$last")
        onStopScroll.invoke(first, last)
    }
}