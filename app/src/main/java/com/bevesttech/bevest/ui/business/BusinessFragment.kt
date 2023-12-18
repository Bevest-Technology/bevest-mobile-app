package com.bevesttech.bevest.ui.business

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentBusinessBinding
import com.bevesttech.bevest.ui.laporan.LaporanKeuanganActivity


class BusinessFragment : Fragment() {

    private var _binding: FragmentBusinessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBusinessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = mutableListOf<ListItemModel>()
        list.add(ListItemModel("Update Anggaran Inkubasi", R.drawable.ic_note_pencil))
        list.add(ListItemModel("Laporan Keuangan", R.drawable.ic_clipboardtext))
        list.add(ListItemModel("Update Perkembangan Bisnis", R.drawable.ic_projectorscreenchart))
        list.add(ListItemModel("Bagikan Dividen", R.drawable.ic_percent))
        list.add(ListItemModel("Pencairan Dana", R.drawable.ic_wallet))

        showMenu(list)
    }

    private fun showMenu(list: List<ListItemModel>) {
        val rv = binding.recyclerView
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(requireActivity())
        val adapter = BusinessListAdapter(list)
        rv.adapter = adapter

        adapter.setOnItemClickCallback(object : BusinessListAdapter.OnItemClickCallback {
            override fun onItemClicked(position: Int) {
                if (position == 1) {
                    Intent(requireActivity(), LaporanKeuanganActivity::class.java).also {
                        startActivity(it)
                    }
                } else {
                    Toast.makeText(requireActivity(), "Fitur ini belum tersedia.", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}