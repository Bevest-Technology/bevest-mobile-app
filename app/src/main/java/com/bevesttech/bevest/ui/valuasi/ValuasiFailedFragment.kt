package com.bevesttech.bevest.ui.valuasi

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentValuasiFailedBinding
import com.bevesttech.bevest.ui.bevestacademy.ArticleActivity

class ValuasiFailedFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentValuasiFailedBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentValuasiFailedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnAjukanKembali.setOnClickListener(this@ValuasiFailedFragment)
            btnBevestAcademy.setOnClickListener(this@ValuasiFailedFragment)

            }
        }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_ajukan_kembali -> {

            }

            R.id.btn_bevest_academy -> {
                Intent(requireActivity(), ArticleActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}

