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


class detalAdapter(): PagingDataAdapter<com.example.testandoid.ui.main.data.Result, detalAdapter.Holder>(rewievComporator) {
    object rewievComporator : DiffUtil.ItemCallback<com.example.testandoid.ui.main.data.Result>() {
        override fun areItemsTheSame(oldItem: com.example.testandoid.ui.main.data.Result, newItem: com.example.testandoid.ui.main.data.Result): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: com.example.testandoid.ui.main.data.Result, newItem: com.example.testandoid.ui.main.data.Result): Boolean {
            return oldItem.link ==newItem.link
        }
    }
    class Holder(val view:View):RecyclerView.ViewHolder(view) {
        val title: TextView = itemView.findViewById(R.id.title_item)
        val body: TextView = itemView.findViewById(R.id.body_item)
        val data: TextView = itemView.findViewById(R.id.data_item)
        val name: TextView = itemView.findViewById(R.id.name_item)
        val foto: ImageView = itemView.findViewById(R.id.photo_item)
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val ItemsViewModel = getItem(position)!!
        Glide.with(holder.itemView.context)
            .load(ItemsViewModel?.multimedia?.src)
            .placeholder(R.drawable.img)
            .error(R.drawable.img)
            .into(holder.foto)
        holder.body.text = ItemsViewModel?.summary_short
        holder.data.text = ItemsViewModel?.byline
        holder.name.text = ItemsViewModel?.display_title
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_reviews, parent, false)
        return Holder(itemView)
    }
}





