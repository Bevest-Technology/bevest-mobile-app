package com.bevesttech.bevest.ui.valuasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentValuasiSuccessBinding

class ValuasiSuccessFragment : Fragment() {
    private var _binding: FragmentValuasiSuccessBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentValuasiSuccessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}