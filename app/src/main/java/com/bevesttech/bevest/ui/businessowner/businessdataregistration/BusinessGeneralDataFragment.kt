package com.bevesttech.bevest.ui.businessowner.businessdataregistration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentBusinessGeneralDataBinding

class BusinessGeneralDataFragment : Fragment() {
    private var _binding: FragmentBusinessGeneralDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBusinessGeneralDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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