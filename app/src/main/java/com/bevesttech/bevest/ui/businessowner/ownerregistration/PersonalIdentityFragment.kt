package com.bevesttech.bevest.ui.businessowner.ownerregistration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentPersonalIdentityBinding

class PersonalIdentityFragment : Fragment() {
    private var _binding: FragmentPersonalIdentityBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPersonalIdentityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            cardUploadOwnerPhoto.tvHint.text = getString(R.string.unggah_foto_owner)
            edtOwnerName.requestFocus()
            activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PersonalIdentityFragment().apply {

            }
    }
}