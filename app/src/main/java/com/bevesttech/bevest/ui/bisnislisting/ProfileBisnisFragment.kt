package com.bevesttech.bevest.ui.bisnislisting

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentProfileBisnisBinding
import com.bevesttech.bevest.utils.ViewModelFactory
import com.bumptech.glide.Glide
import java.io.File

class ProfileBisnisFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentProfileBisnisBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BisnisListingViewModel by viewModels { ViewModelFactory(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBisnisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListener()
        setView()

    }

    private fun setView() {
        val profileUri = viewModel.fotoProfileUri
        val bannerUri = viewModel.fotoBannerUri
        val productUri = viewModel.fotoProductUri
        if (profileUri != null) {
            Glide.with(requireActivity()).load(profileUri).circleCrop().into(binding.ivProfile)
            binding.edtFotoProfile.setText(" ")
            binding.edtFotoProfile.error = null
        }
        if (bannerUri != null) {
            Glide.with(requireActivity()).load(bannerUri).into(binding.ivBanner)
            binding.edtFotoBanner.setText(" ")
            binding.edtFotoBanner.error = null
        }
        if (productUri != null) {
            Glide.with(requireActivity()).load(productUri).into(binding.ivProduct)
            binding.edtFotoProduct.setText(" ")
            binding.edtFotoProduct.error = null
        }
    }

    private fun setListener() {
        binding.btnFotoProfile.setOnClickListener(this)
        binding.btnFotoBanner.setOnClickListener(this)
        binding.btnFotoProduct.setOnClickListener(this)
        binding.btnLanjut.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_foto_profile -> {
                startGallery(Type.PROFILE)
            }

            R.id.btn_foto_banner -> {
                startGallery(Type.BANNER)
            }

            R.id.btn_foto_product -> {
                startGallery(Type.PRODUCT)
            }

            R.id.btn_lanjut -> {
                checkField()
            }
        }
    }

    private fun checkField() {
        with(binding) {
            var isChecked = true
            if (edtDeskripsiBisnis.text.isNullOrEmpty()) {
                edtDeskripsiBisnis.error = getString(R.string.field_tidak_boleh_kosong)
                isChecked = false
            }
            if (edtProfileOwner.text.isNullOrEmpty()) {
                edtProfileOwner.error = getString(R.string.field_tidak_boleh_kosong)
                isChecked = false
            }
            if (edtVisimisiPerusahaan.text.isNullOrEmpty()) {
                edtVisimisiPerusahaan.error = getString(R.string.field_tidak_boleh_kosong)
                isChecked = false
            }
            if (edtAlamat.text.isNullOrEmpty()) {
                edtAlamat.error = getString(R.string.field_tidak_boleh_kosong)
                isChecked = false
            }
            if (edtTotalCabang.text.isNullOrEmpty()) {
                edtTotalCabang.error = getString(R.string.field_tidak_boleh_kosong)
                isChecked = false
            }
            if (viewModel.fotoProfileUri == null) {
                edtFotoProfile.error = getString(R.string.field_tidak_boleh_kosong)
                isChecked = false
            }
            if (viewModel.fotoProductUri == null) {
                edtFotoProduct.error = getString(R.string.field_tidak_boleh_kosong)
                isChecked = false
            }
            if (viewModel.fotoBannerUri == null) {
                edtFotoBanner.error = getString(R.string.field_tidak_boleh_kosong)
                isChecked = false
            }
            if (isChecked){
                viewModel.setProfileBisnis(
                    deskripsi = edtDeskripsiBisnis.text.toString(),
                    profileOwner = edtProfileOwner.text.toString(),
                    visiMisi = edtVisimisiPerusahaan.text.toString(),
                    alamat = edtAlamat.text.toString(),
                    totalCabang = edtTotalCabang.text.toString().toInt(),
                    fotoProfile = viewModel.fotoProfileUri!!,
                    fotoBanner = viewModel.fotoBannerUri!!,
                    fotoProduct = viewModel.fotoProductUri!!,
                    video = edtVideo.text.toString()
                )
                val fragmentManager = parentFragmentManager
                val keuanganFragment = KeuanganFragment()
                fragmentManager.commit {
                    replace(
                        R.id.fragment_bisnislisting_container,
                        keuanganFragment,
                        KeuanganFragment::class.java.simpleName
                    )
                    addToBackStack(null)
                }
            }
        }
    }

    private fun startGallery(type: Type) {
        when (type) {
            Type.PROFILE -> {
                launchFotoProfile.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }

            Type.BANNER -> {
                launchFotoBanner.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }

            Type.PRODUCT -> {
                launchFotoProduct.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        }
    }

    private val launchFotoProfile = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            viewModel.setFotoProfileUri(uri)
            setView()
        }
    }
    private val launchFotoBanner = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            viewModel.setFotoBannerUri(uri)
            setView()
        }
    }
    private val launchFotoProduct = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            viewModel.setFotoProductUri(uri)
            setView()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}