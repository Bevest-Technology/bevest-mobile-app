package com.bevesttech.bevest.ui.business

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.ListItemBusinessBinding

class BusinessListAdapter(private val listItem: List<ListItemModel>): RecyclerView.Adapter<BusinessListAdapter.ViewHolder>(){
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(position: Int)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(var binding: ListItemBusinessBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBusinessBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = listItem[position]
        val title = list.title
        val icon = list.icon
        holder.binding.icon.setImageResource(icon)
        holder.binding.text.text = title

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(position) }
    }

}