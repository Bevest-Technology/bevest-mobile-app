package com.bevesttech.bevest.ui.businessowner.ownerregistration

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentOwnerKtpUploadBinding
import com.bevesttech.bevest.utils.ViewModelFactory
import com.bevesttech.bevest.utils.getImageUri
import com.bevesttech.bevest.utils.invisible
import com.bevesttech.bevest.utils.visible


class OwnerKtpUploadFragment : Fragment() {
    private var _binding: FragmentOwnerKtpUploadBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: OwnerRegistrationViewModel by activityViewModels { ViewModelFactory(requireActivity()) }
    private var currentImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOwnerKtpUploadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()
        setListener()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setView() {
        with(binding) {
            cardUploadKtp.tvHint.text = getString(R.string.ambil_foto_ktp)
        }
    }

    private fun setListener() {
        with(binding) {
            cardUploadKtp.root.setOnClickListener {
                startCamera()
            }
        }
    }

    private fun startCamera() {
        currentImageUri = getImageUri(requireContext())
        launcherIntentCamera.launch(currentImageUri)
        if (currentImageUri != null) {
            sharedViewModel.setHasUploadKtp(true)
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
        }
    }

    private fun showImage() {
        with(binding) {
            currentImageUri?.let {
                cardUploadKtp.ivPreview.apply {
                    visible()
                    setImageURI(it)
                }

                cardUploadKtp.tvHint.apply {
                    text = context.getString(R.string.berhasil_unggah_foto_ktp)
                    setTextColor(ContextCompat.getColor(context, R.color.ds_primary))
                }

                cardUploadKtp.root.strokeColor =
                    ContextCompat.getColor(requireContext(), R.color.ds_primary)

                cardUploadKtp.ibUpload.invisible()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OwnerKtpUploadFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}