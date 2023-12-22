package com.bevesttech.bevest.ui.investor.profileresiko

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentProfileResikoResultBinding

class ProfileResikoResultFragment : Fragment() {
    private var _binding: FragmentProfileResikoResultBinding? = null
    private val binding get() = _binding!!

    var tipeInvestor: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileResikoResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null) {
            val tipeFromBundle = savedInstanceState.getString(EXTRA_TIPE)
            tipeInvestor = tipeFromBundle
        }

        binding.tvTipeInvestor.text = tipeInvestor
        binding.btnLanjut.setOnClickListener {
            val fragmentManager = parentFragmentManager
            val fragmentRecomendation = InvestorRecommendationFragment()
            fragmentManager.commit {
                replace(
                    R.id.fragment_container,
                    fragmentRecomendation,
                    InvestorRecommendationFragment::class.java.simpleName
                )
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
      const val EXTRA_TIPE = "extra_tipe"
    }
}