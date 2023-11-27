package com.bevesttech.bevest.ui.valuasi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentPhoneCallBinding

class PhoneCallFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentPhoneCallBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhoneCallBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnLanjut.setOnClickListener(this@PhoneCallFragment)
            btnChangePhoneNumber.setOnClickListener(this@PhoneCallFragment)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun onClick(view: View) {
        val fragmentManager = parentFragmentManager
        val callProcessFragment = CallProcessFragment()
        val changePhoneNumberFragment = ChangePhoneNumberFragment()

        when (view.id) {
            R.id.btn_lanjut -> {
                fragmentManager.commit {
                    replace(R.id.fragment_valuasi_container, callProcessFragment, CallProcessFragment::class.java.simpleName)
                }
            }
            R.id.btn_change_phone_number -> {
                fragmentManager.commit {
                    addToBackStack(null)
                    replace(R.id.fragment_valuasi_container, changePhoneNumberFragment, ChangePhoneNumberFragment::class.java.simpleName)
                }
            }
        }
    }
}