package com.bevesttech.bevest.ui.investor.investorpersonaldata

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentOwnerKtpUploadBinding
import com.bevesttech.bevest.ui.businessowner.ownerregistration.PersonalIdentityFragment
import com.bevesttech.bevest.utils.ViewModelFactory
import com.bevesttech.bevest.utils.getImageUri
import com.bevesttech.bevest.utils.invisible
import com.bevesttech.bevest.utils.visible

class InvestorKtpUploadFragment : Fragment() {
    private var _binding: FragmentOwnerKtpUploadBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: InvestorPersonalDataViewModel by activityViewModels {
        ViewModelFactory(
            requireActivity()
        )
    }
    private var currentImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOwnerKtpUploadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(InvestorKtpUploadFragment.REQUIRED_PERMISSION)
        }

        setView()
        setListener()
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

    private fun setView() {
        with(binding) {
            cardUploadKtp.tvHint.text = getString(R.string.ambil_foto_ktp)
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
                Toast.makeText(requireContext(), "Permission request granted", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(requireContext(), "Permission request denied", Toast.LENGTH_LONG)
                    .show()
            }
        }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            requireContext(),
            InvestorKtpUploadFragment.REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InvestorKtpUploadFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}