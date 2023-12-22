package com.bevesttech.bevest.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentProfileBinding
import com.bevesttech.bevest.ui.login.LoginActivity
import com.bevesttech.bevest.utils.ViewModelFactory
import com.bumptech.glide.Glide

class ProfileFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels {
        ViewModelFactory(requireActivity())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListener()
        setProfile()
    }

    private fun setProfile(nama: String? = null, image: Uri? = null) {
        if (nama != null && image != null) {
            binding.tvNamaBisnis.text = nama
            Glide.with(requireActivity()).load(image).circleCrop().into(binding.ivProfilePhoto)
        }
    }

    private fun setListener() {
        binding.layoutKeluar.setOnClickListener(this)
        binding.layoutEditProfile.setOnClickListener(this)
        binding.layoutFaq.setOnClickListener(this)
        binding.layoutSecurity.setOnClickListener(this)
        binding.layoutLiveSupport.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.layout_keluar -> {
                viewModel.logout()
                Intent(requireActivity(), LoginActivity::class.java).also {
                    startActivity(it)
                    requireActivity().finishAffinity()
                }
            }

            else -> {
                Toast.makeText(requireActivity(), "Fitur ini belum tersedia.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}