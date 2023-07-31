package com.example.testandroiiiiiiid.adapter

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testandoid.ui.main.data.Result
import com.example.testandroiiiiiiid.R

class criticAdapter(): PagingDataAdapter<com.example.testandroiiiiiiid.dataCritic.Result, criticAdapter.Holder>(criticComporator) {
    object criticComporator : DiffUtil.ItemCallback<com.example.testandroiiiiiiid.dataCritic.Result>() {
        override fun areItemsTheSame(oldItem: com.example.testandroiiiiiiid.dataCritic.Result, newItem: com.example.testandroiiiiiiid.dataCritic.Result): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: com.example.testandroiiiiiiid.dataCritic.Result, newItem: com.example.testandroiiiiiiid.dataCritic.Result): Boolean {
            return oldItem.bio ==newItem.bio
        }
    }
    class Holder(val view:View):RecyclerView.ViewHolder(view) {
        val foto: ImageView = itemView.findViewById(R.id.imageActor)
        val name: TextView = itemView.findViewById(R.id.nameActor)

    }
    private var onClickListener: OnClickListener? = null
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val ItemsViewModel = getItem(position)!!
        Glide.with(holder.itemView.context)
            .load(ItemsViewModel?.multimedia?.resource?.src)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.foto)
        holder.name.text = ItemsViewModel.display_name
        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, ItemsViewModel )
            }
        }
    }
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }
    interface OnClickListener {
        fun onClick(position: Int, model: com.example.testandroiiiiiiid.dataCritic.Result)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_critic, parent, false)
        return Holder(itemView)
    }


}
