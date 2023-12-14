package com.bevesttech.bevest.ui.businessowner.businessdataregistration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentBusinessEntityDataBinding
class BusinessEntityDataFragment : Fragment() {
    private var _binding: FragmentBusinessEntityDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBusinessEntityDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val kategoriBisnis: Array<String> = resources.getStringArray(R.array.kategori_bisnis_list)
        val arrayAdapterKategoriBisnis: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_expandable_list_item_1, kategoriBisnis)

        with(binding) {
            cardUploadBusinessBanner.tvHint.text = getString(R.string.unggah_bukti_brand_usaha_banner)
            actBusinessCategory.setAdapter(arrayAdapterKategoriBisnis)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BusinessEntityDataFragment().apply {
            }
    }
}