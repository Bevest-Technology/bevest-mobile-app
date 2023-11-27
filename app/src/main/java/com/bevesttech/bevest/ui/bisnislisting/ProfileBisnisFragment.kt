package com.bevesttech.bevest.ui.bisnislisting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentEmailAtForgotPasswordBinding
import com.bevesttech.bevest.databinding.FragmentProfileBisnisBinding

class ProfileBisnisFragment : Fragment() {
    private var _binding: FragmentProfileBisnisBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBisnisBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}