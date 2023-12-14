package com.bevesttech.bevest.ui.businessowner.ownerregistration

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.databinding.adapters.ViewGroupBindingAdapter.setListener
import androidx.fragment.app.Fragment
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentPersonalIdentityBinding
import com.bevesttech.bevest.utils.gone
import com.bevesttech.bevest.utils.invisible
import com.bevesttech.bevest.utils.visible

class PersonalIdentityFragment : Fragment() {
    private var _binding: FragmentPersonalIdentityBinding? = null
    private val binding get() = _binding!!
    private var currentImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPersonalIdentityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }

        with(binding) {
            cardUploadOwnerPhoto.tvHint.text = getString(R.string.unggah_foto_owner)
            edtOwnerName.requestFocus()
            activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        }

        setListener()
    }

    private fun setListener() {
        with(binding) {
            cardUploadOwnerPhoto.root.setOnClickListener {
                startGallery()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(requireContext(), "Permission request granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Permission request denied", Toast.LENGTH_LONG).show()
            }
        }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            requireContext(),
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun showImage() {
        with(binding) {
            currentImageUri?.let {
                cardUploadOwnerPhoto.ivPreview.apply {
                    visible()
                    setImageURI(it)
                }

                cardUploadOwnerPhoto.tvHint.apply {
                    text =  getString(R.string.berhasil_unggah_foto_owner)
                    setTextColor(ContextCompat.getColor(context, R.color.ds_primary))
                }

                cardUploadOwnerPhoto.root.strokeColor = ContextCompat.getColor(requireContext(), R.color.ds_primary)

                cardUploadOwnerPhoto.ibUpload.invisible()
            }
        }
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PersonalIdentityFragment().apply {

            }
    }
}