package com.bevesttech.bevest.ui.beranda

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentBerandaBusinessBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class BerandaBusinessFragment : Fragment() {

    private var _binding: FragmentBerandaBusinessBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBerandaBusinessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()
        setListener()
    }

    private fun setListener() {
        binding.btnLanjutIsiData.setOnClickListener {
            Toast.makeText(requireActivity(), "Lanjut Isi Data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setView() {
        binding.tvName.text = "John Doe"
        Glide.with(requireActivity()).apply {
            //Avatar
            load(R.drawable.bevest_logo).circleCrop().into(binding.ivProfileAvatar)
            //Banner
            load(R.drawable.image_linear_gradient).transform(RoundedCorners(16)).into(binding.ivBanner)
        }

        val listKembangkanBisnis = listOf<CardBerandaModel>()
        val listCeritaSukses = listOf<CardBerandaModel>()

        showRecyclerView(listKembangkanBisnis, listCeritaSukses)
    }

    private fun showRecyclerView(listKembangkanBisnis: List<CardBerandaModel>, listCeritaSukses: List<CardBerandaModel>) {
        val rvKembangkanBisnis = binding.rvKembangkanBisnis
        val kembangkanBisnisdapter = CardBerandaAdapter(listKembangkanBisnis)
        rvKembangkanBisnis.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            adapter = kembangkanBisnisdapter
        }
        val rvCeritaSukses = binding.rvCeritaSukses
        val ceritaSuksesAdapter = CardBerandaAdapter(listCeritaSukses)
        rvCeritaSukses.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            adapter = ceritaSuksesAdapter
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}