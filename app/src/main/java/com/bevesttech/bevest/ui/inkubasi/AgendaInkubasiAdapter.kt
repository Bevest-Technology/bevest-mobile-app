package com.bevesttech.bevest.ui.inkubasi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bevesttech.bevest.databinding.ItemAgendaInkubasiBinding

class AgendaInkubasiAdapter: RecyclerView.Adapter<AgendaInkubasiAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemAgendaInkubasiBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAgendaInkubasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}