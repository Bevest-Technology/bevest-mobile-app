package com.bevesttech.bevest.ui.investor.profileresiko

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentInvestorRecomendationBinding
import com.bevesttech.bevest.ui.investor.register.ProfileResikoViewModel
import com.bevesttech.bevest.utils.ViewModelFactory


class InvestorRecommendationFragment : Fragment() {

    private var _binding: FragmentInvestorRecomendationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileResikoViewModel by viewModels {
        ViewModelFactory(requireActivity())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInvestorRecomendationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvRekomendasiMenabung.text = viewModel.rekomendasiMenabung
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}