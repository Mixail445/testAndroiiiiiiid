package com.example.testandroiiiiiiid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testandroiiiiiiid.R
import com.example.testandroiiiiiiid.Utils.visible
import com.example.testandroiiiiiiid.databinding.ItemLoadStateReviewBinding

class loadStateAdapter (  private val retry: () -> Unit): LoadStateAdapter<loadStateAdapter.loadHolder>() {
    class loadHolder(        private val binding: ItemLoadStateReviewBinding,private val retry: () -> Unit
    ):RecyclerView.ViewHolder(binding.root) {
fun bind(loadState: LoadState){
    if (loadState is LoadState.Error) {
        binding.textViewError.text = loadState.error.localizedMessage
    }
    binding.progressbar.visible(loadState is LoadState.Loading)
    binding.buttonRetry.visible(loadState is LoadState.Error)
    binding.textViewError.visible(loadState is LoadState.Error)
    binding.buttonRetry.setOnClickListener {
        retry()
    }

    binding.progressbar.visibility = View.VISIBLE
}
}



    override fun onBindViewHolder(holder: loadHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState)
        = loadHolder(
            ItemLoadStateReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            retry
        )

    }
