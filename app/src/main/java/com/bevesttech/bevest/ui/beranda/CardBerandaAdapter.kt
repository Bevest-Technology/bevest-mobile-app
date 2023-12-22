package com.bevesttech.bevest.ui.beranda

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bevesttech.bevest.databinding.CardBerandaItemBinding
import com.bumptech.glide.Glide

class CardBerandaAdapter(private val listItem: List<CardBerandaModel>): RecyclerView.Adapter<CardBerandaAdapter.ViewHolder>() {
    class ViewHolder(var binding: CardBerandaItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardBerandaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItem[position]
        val title = item.title
        val image = item.image
        holder.binding.tvTitle.text = title
        Glide.with(holder.itemView.context).load(image).into(holder.binding.ivImage)
    }
}