package com.bevesttech.bevest.ui.inkubasi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentInkubasiBinding

class InkubasiFragment : Fragment() {
    private var _binding: FragmentInkubasiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInkubasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCekProggress.setOnClickListener {
            Intent(Intent.ACTION_VIEW, Uri.parse("https://inkubator.lpdb.id/")).also {
                startActivity(it)
            }
        }

        val listAgendaInkubasi = listOf<ItemInkubasiModel>()
        setView(listAgendaInkubasi)
    }

    private fun setView(list: List<ItemInkubasiModel>) {
        if (list.isNotEmpty()) {
            val rv = binding.rvInkubasi
            val rvAdapter = AgendaInkubasiAdapter(list)
            rv.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireActivity())
                adapter = rvAdapter
            }
        } else {
            binding.agendaEmpty.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}