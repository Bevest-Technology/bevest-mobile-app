package com.bevesttech.bevest.ui.investor.investorpersonaldata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentInvestorBankAccountBinding
import com.bevesttech.bevest.utils.ViewModelFactory


class InvestorBankAccountFragment : Fragment() {
    private var _binding: FragmentInvestorBankAccountBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: InvestorPersonalDataViewModel by activityViewModels { ViewModelFactory(requireActivity()) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInvestorBankAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()
        setObserver()
    }

    private fun setObserver() {
        sharedViewModel.bankAccountFormState.observe(viewLifecycleOwner) { state ->
            with(binding) {
                if (state.bankNameError != null) {
                    actBankName.error = getString(state.bankNameError)
                }
                if (state.bankNumberError != null) {
                    edtBankNumber.error = getString(state.bankNumberError)
                }
            }

        }
    }

    private fun setView() {
        val bankList: Array<String> = resources.getStringArray(R.array.bank_list)
        val arrayAdapterBank: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_expandable_list_item_1, bankList)

        with(binding) {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            actBankName.setAdapter(arrayAdapterBank)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InvestorBankAccountFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}