package com.bevesttech.bevest.ui.businessowner.businessdataregistration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentBusinessGeneralDataBinding
import com.bevesttech.bevest.utils.ViewModelFactory

class BusinessGeneralDataFragment : Fragment() {
    private var _binding: FragmentBusinessGeneralDataBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: BusinessDataRegistrationViewModel by activityViewModels { ViewModelFactory(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBusinessGeneralDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()
        setObserver()
    }

    private fun setObserver() {
        sharedViewModel.businessGeneralDataFormState.observe(viewLifecycleOwner) { state ->
            with(binding) {
                if (state.mainProductError != null) {
                    edtProdukUtama.error = getString(state.mainProductError)
                }
                if (state.marketTargetError != null) {
                    actTargetPasar.error = getString(state.marketTargetError)
                }
                if (state.legalEntityTypeError != null) {
                    actBadanHukum.error = getString(state.legalEntityTypeError)
                }
                if (state.salesSystemTypeError != null) {
                    actSistemPenjualan.error = getString(state.salesSystemTypeError)
                }
            }
        }
    }

    private fun setView() {
        val marketTarget: Array<String> = resources.getStringArray(R.array.market_target)
        val arrayAdapterMarketTarget: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_expandable_list_item_1, marketTarget)

        val legalEntityType: Array<String> = resources.getStringArray(R.array.business_legal_type)
        val arrayAdapterLegalEntityType: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_expandable_list_item_1, legalEntityType)

        val salesSystemType: Array<String> = resources.getStringArray(R.array.selling_system)
        val arrayAdapterSalesSystemType: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_expandable_list_item_1, salesSystemType)

        with(binding) {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            actTargetPasar.setAdapter(arrayAdapterMarketTarget)
            actBadanHukum.setAdapter(arrayAdapterLegalEntityType)
            actSistemPenjualan.setAdapter(arrayAdapterSalesSystemType)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BusinessGeneralDataFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}