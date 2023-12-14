package com.bevesttech.bevest.ui.businessowner.ownerregistration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentHaveBusinessEntityBinding
import com.bevesttech.bevest.utils.ViewModelFactory

class HaveBusinessEntityFragment : Fragment() {
    private var _binding: FragmentHaveBusinessEntityBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: OwnerRegistrationViewModel by activityViewModels { ViewModelFactory(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHaveBusinessEntityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            fragment = this@HaveBusinessEntityFragment
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HaveBusinessEntityFragment().apply {
            }
    }
}