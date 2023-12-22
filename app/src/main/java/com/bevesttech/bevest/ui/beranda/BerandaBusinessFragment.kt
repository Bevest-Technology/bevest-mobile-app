package com.bevesttech.bevest.ui.beranda

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentBerandaBusinessBinding
import com.bevesttech.bevest.ui.bisnislisting.BisnisListingActivity
import com.bevesttech.bevest.ui.businesshome.BusinessHomeActivity
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
            Intent(requireActivity(), BisnisListingActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun setView() {
        binding.tvName.text = "John Doe"
        Glide.with(requireActivity()).apply {
            //Avatar
            load(R.drawable.bevest_logo).circleCrop().into(binding.ivProfileAvatar)
            //Banner
            load(R.drawable.kodakopi_banner).transform(RoundedCorners(16)).into(binding.ivBanner)
        }


        val listKembangkanBisnis = makeDummyData()
        val listCeritaSukses = makeDummyData()

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

    fun makeDummyData(): List<CardBerandaModel> {
        return listOf<CardBerandaModel>(
            CardBerandaModel(
                "https://i1.wp.com/www.maxmanroe.com/vid/wp-content/uploads/2017/12/Pengertian-UMKM.png?fit=700%2C363&ssl=1".toUri(),
                "Kembangkan Bisnis Anda bersama Bevest, Dapatkan Pendanaan hingga Rp1 Miliar"
            ),
            CardBerandaModel(
                "https://thumbs.dreamstime.com/b/ecuadorian-women-sell-plant-fiber-baskets-ecuador-cuenca-november-two-esmeraldas-province-their-handmade-made-artisans-155879600.jpg".toUri(),
                "Manajemen Keuangan Usaha untuk Pelaku UMKM yang Harus Anda Terapkan"
            ),
            CardBerandaModel(
                "https://th.bing.com/th/id/OIP.fFcnPGX5i8xup-fcOrDxFAHaE6?rs=1&pid=ImgDetMain".toUri(),
                "(UMKM): Pengertian, Jenis, dan Peranannya di Indonesia"
            ),
        )
    }

}