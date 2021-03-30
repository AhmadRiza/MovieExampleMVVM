package com.github.ahmadriza.movie.ui.home

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.ahmadriza.movie.R
import com.github.ahmadriza.movie.databinding.ViewLoadStateBinding
import com.github.ahmadriza.movie.utils.getBindingOf

class MovieLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<MovieLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {

        val progress = holder.loadStateViewBinding.loadStateProgress
        val btnRetry = holder.loadStateViewBinding.loadStateRetry
        val txtErrorMessage = holder.loadStateViewBinding.loadStateErrorMessage

        btnRetry.isVisible = loadState !is LoadState.Loading
        txtErrorMessage.isVisible = loadState !is LoadState.Loading
        progress.isVisible = loadState is LoadState.Loading

        if (loadState is LoadState.Error){
            txtErrorMessage.text = loadState.error.localizedMessage
        }

        btnRetry.setOnClickListener {
            retry.invoke()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            parent.getBindingOf(R.layout.view_load_state)
        )
    }

    class LoadStateViewHolder(val loadStateViewBinding: ViewLoadStateBinding) :
        RecyclerView.ViewHolder(loadStateViewBinding.root)
}