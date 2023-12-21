package com.bevesttech.bevest.ui.businessowner.businessdataregistration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentBusinessSalesValuationBinding
import com.bevesttech.bevest.utils.ViewModelFactory

class BusinessSalesValuationFragment : Fragment() {
    private var _binding: FragmentBusinessSalesValuationBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: BusinessDataRegistrationViewModel by activityViewModels { ViewModelFactory(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBusinessSalesValuationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()
        setObserver()
    }

    private fun setObserver() {
        sharedViewModel.businessSalesValuationFormState.observe(viewLifecycleOwner) { state ->
            with(binding) {
                if (state.averageAnnualSalesError != null) {
                    edtPenjualanRataRata.error = getString(state.averageAnnualSalesError)
                }
                if (state.assetTotalError != null) {
                    edtTotalAset.error = getString(state.assetTotalError)
                }
                if (state.creditAssetCollateralError != null) {
                    edtNilaiJaminanAsetKredit.error = getString(state.creditAssetCollateralError)
                }
                if (state.employeesNumberError != null) {
                    edtJumlahKaryawan.error = getString(state.employeesNumberError)
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
            BusinessSalesValuationFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}