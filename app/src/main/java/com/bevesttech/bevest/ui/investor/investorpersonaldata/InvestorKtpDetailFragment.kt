package com.bevesttech.bevest.ui.investor.investorpersonaldata

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentInvestorKtpDetailBinding
import com.bevesttech.bevest.utils.ViewModelFactory

class InvestorKtpDetailFragment : Fragment() {
    private var _binding: FragmentInvestorKtpDetailBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: InvestorPersonalDataViewModel by activityViewModels { ViewModelFactory(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInvestorKtpDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()
        setObserver()
    }

    private fun setObserver() {
        sharedViewModel.detailKtpFormState.observe(viewLifecycleOwner) { state ->
            with(binding) {
                if (state.nikError != null) {
                    edtNik.error = getString(state.nikError)
                }
                if (state.placeOfBirthError != null) {
                    edtTempatLahir.error = getString(state.placeOfBirthError)
                }
                if (state.genderError != null) {
                    actGender.error = getString(state.genderError)
                }
                if (state.addressError != null) {
                    edtAlamat.error = getString(state.addressError)
                }
                if (state.rtRwError != null) {
                    edtRtR.error = getString(state.rtRwError)
                }
                if (state.religionError != null) {
                    actAgama.error = getString(state.religionError)
                }
                if (state.maritalStatusError != null) {
                    actStatusPerkawinan.error = getString(state.maritalStatusError)
                }
                if (state.jobError != null) {
                    edtPekerjaan.error = getString(state.jobError)
                }
                if (state.citizenshipError != null) {
                    actKewarganegaraan.error = getString(state.citizenshipError)
                }
            }
        }
    }

    private fun setView() {
        val genderList: Array<String>  = resources.getStringArray(R.array.gender_list)
        val arrayAdapterGender: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_expandable_list_item_1, genderList)

        val religionList: Array<String>  = resources.getStringArray(R.array.religion_list)
        val arrayAdapterReligion: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_expandable_list_item_1, religionList)

        val maritalStatusList: Array<String>  = resources.getStringArray(R.array.marital_status_list)
        val arrayAdapterMaritalStatus: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_expandable_list_item_1, maritalStatusList)

        val citizenshipList: Array<String>  = resources.getStringArray(R.array.citizenship_status_list)
        val arrayAdapterCitizenship: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_expandable_list_item_1, citizenshipList)

        with(binding) {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            actGender.setAdapter(arrayAdapterGender)
            actAgama.setAdapter(arrayAdapterReligion)
            actStatusPerkawinan.setAdapter(arrayAdapterMaritalStatus)
            actKewarganegaraan.setAdapter(arrayAdapterCitizenship)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InvestorKtpDetailFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}