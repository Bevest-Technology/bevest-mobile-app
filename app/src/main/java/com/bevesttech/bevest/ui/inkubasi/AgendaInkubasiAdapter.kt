package com.bevesttech.bevest.ui.inkubasi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bevesttech.bevest.databinding.ItemAgendaInkubasiBinding

class AgendaInkubasiAdapter(private val listInkubasi: List<ItemInkubasiModel>): RecyclerView.Adapter<AgendaInkubasiAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemAgendaInkubasiBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAgendaInkubasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listInkubasi.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listInkubasi[position]
        val status = item.status
        val title = item.title
        val date = item.date
        val location = item.location

        holder.binding.apply {
            tvTitle.text = title
            tvKeterangan.text = status
            tvDate.text = date
            tvLocation.text = location
        }
    }
}