package com.bevesttech.bevest.ui.businessowner.ownerregistration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentHaveBusinessEntityBinding

class HaveBusinessEntityFragment : Fragment() {
    private var _binding: FragmentHaveBusinessEntityBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHaveBusinessEntityBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HaveBusinessEntityFragment().apply {
            }
    }
}