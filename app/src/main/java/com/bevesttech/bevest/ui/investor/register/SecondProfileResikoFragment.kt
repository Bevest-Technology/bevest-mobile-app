package com.bevesttech.bevest.ui.investor.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bevesttech.bevest.R
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.databinding.FragmentSecondProfileResikoBinding
import com.bevesttech.bevest.utils.ViewModelFactory
import com.bevesttech.bevest.utils.gone
import com.bevesttech.bevest.utils.hideKeyboard
import com.bevesttech.bevest.utils.visible


class SecondProfileResikoFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentSecondProfileResikoBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: ProfileResikoViewModel by activityViewModels {
        ViewModelFactory(
            requireActivity()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSecondProfileResikoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        setListener()
    }

    private fun setListener() {
        binding.actJenisKelamin.onItemClickListener =
            AdapterView.OnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
                binding.actJenisKelamin.error = null
            }
        binding.actStatusPernikahan.onItemClickListener =
            AdapterView.OnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
                binding.actStatusPernikahan.error = null
            }
        binding.actKepemilikanRumah.onItemClickListener =
            AdapterView.OnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
                binding.actKepemilikanRumah.error = null
            }
        binding.actPendidikanTerakhir.onItemClickListener =
            AdapterView.OnItemClickListener { parent: AdapterView<*>, view: View, position: Int, id: Long ->
                binding.actPendidikanTerakhir.error = null
            }
        binding.btnLanjut.setOnClickListener(this)
    }

    private fun setAdapter() {
        val adapterJenisKelamin = ArrayAdapter(
            requireActivity(),
            R.layout.dropdown_list_item,
            resources.getStringArray(R.array.gender_list)
        )
        val adapterKepemilikanRumah = ArrayAdapter(
            requireActivity(),
            R.layout.dropdown_list_item,
            resources.getStringArray(R.array.kepemilikan_rumah)
        )
        val adapterPendidikanTerakhir = ArrayAdapter(
            requireActivity(),
            R.layout.dropdown_list_item,
            resources.getStringArray(R.array.pendidikan_terakhir)
        )
        val adapterStatusPernikahan = ArrayAdapter(
            requireActivity(),
            R.layout.dropdown_list_item,
            resources.getStringArray(R.array.marital_status_list)
        )
        binding.actJenisKelamin.setAdapter(adapterJenisKelamin)
        binding.actKepemilikanRumah.setAdapter(adapterKepemilikanRumah)
        binding.actPendidikanTerakhir.setAdapter(adapterPendidikanTerakhir)
        binding.actStatusPernikahan.setAdapter(adapterStatusPernikahan)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_lanjut) {
            val jenisKelamin = binding.actJenisKelamin.text
            val kepemilikanRumah = binding.actKepemilikanRumah.text
            val pendidikanTerakhir = binding.actPendidikanTerakhir.text
            val statusPernikahan = binding.actStatusPernikahan.text
            var valid = true

            if (jenisKelamin.isNullOrEmpty()) {
                binding.actJenisKelamin.error = "Field ini tidak boleh kosong!"
                valid = false
            }
            if (kepemilikanRumah.isNullOrEmpty()) {
                binding.actKepemilikanRumah.error = "Field ini tidak boleh kosong!"
                valid = false
            }
            if (pendidikanTerakhir.isNullOrEmpty()) {
                binding.actPendidikanTerakhir.error = "Field ini tidak boleh kosong!"
                valid = false
            }
            if (statusPernikahan.isNullOrEmpty()) {
                binding.actStatusPernikahan.error = "Field ini tidak boleh kosong!"
                valid = false
            }

            if (valid) {
                with(binding) {
                    sharedViewModel.setInvestorProfilingData()
                        .observe(viewLifecycleOwner) { state ->
                            when (state) {
                                is Result.Loading -> {
                                    progressIndicator.visible()
                                    hideKeyboard()
                                }

                                is Result.Success -> {
                                    progressIndicator.gone()

                                    Intent(requireActivity(), RiskProfileResultActivity::class.java).also {
                                        startActivity(it)
                                        requireActivity().finish()
                                    }
                                }

                                is Result.Error -> {
                                    progressIndicator.gone()
                                    Toast.makeText(
                                        requireContext(),
                                        state.error,
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                }

            }
        }
    }

    companion object {
        const val EXTRA_MODEL = "extra_model"
    }
}