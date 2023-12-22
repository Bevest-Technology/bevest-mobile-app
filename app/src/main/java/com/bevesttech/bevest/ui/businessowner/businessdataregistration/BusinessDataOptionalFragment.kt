package com.bevesttech.bevest.ui.businessowner.businessdataregistration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentBusinessDataOptionalBinding
import com.bevesttech.bevest.utils.ViewModelFactory

class BusinessDataOptionalFragment : Fragment() {
    private var _binding: FragmentBusinessDataOptionalBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: BusinessDataRegistrationViewModel by activityViewModels { ViewModelFactory(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBusinessDataOptionalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()
        setObserver()
    }

    private fun setObserver() {
        sharedViewModel.businessDataOptionalFormState.observe(viewLifecycleOwner) { state ->
            with(binding) {
                if (state.creditDocumentNumberError != null) {
                    edtDokumenKredit.error = getString(state.creditDocumentNumberError)
                }
            }
        }
    }

    private fun setView() {
        with(binding) {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BusinessDataOptionalFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}