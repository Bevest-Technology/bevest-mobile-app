package com.bevesttech.bevest.ui.investor.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentFirstProfileResikoBinding
import com.bevesttech.bevest.databinding.FragmentSecondProfileResikoBinding
import com.bevesttech.bevest.utils.ViewModelFactory


class SecondProfileResikoFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentSecondProfileResikoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileResikoViewModel by viewModels {
        ViewModelFactory(requireActivity())
    }

    var models: ProfileResikoModel? = null
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
        if (savedInstanceState != null) {
            val modelFromBundle = savedInstanceState.getParcelable<ProfileResikoModel>(EXTRA_MODEL)
            models = modelFromBundle
        }

        setAdapter()
        setListener()
    }

    private fun setListener() {
        binding.actJenisKelamin.onItemClickListener = AdapterView.OnItemClickListener{
            parent: AdapterView<*>, view: View, position: Int, id: Long ->
            binding.actJenisKelamin.error = null
        }
        binding.actKepemilikanRumah.onItemClickListener = AdapterView.OnItemClickListener{
                parent: AdapterView<*>, view: View, position: Int, id: Long ->
            binding.actKepemilikanRumah.error = null
        }
        binding.actPendidikanTerakhir.onItemClickListener = AdapterView.OnItemClickListener{
                parent: AdapterView<*>, view: View, position: Int, id: Long ->
            binding.actPendidikanTerakhir.error = null
        }
        binding.btnLanjut.setOnClickListener(this)
    }

    private fun setAdapter() {
        val adapterJenisKelamin = ArrayAdapter(requireActivity(), R.layout.dropdown_list_item, resources.getStringArray(R.array.jenis_kelamin))
        val adapterKepemilikanRumah = ArrayAdapter(requireActivity(), R.layout.dropdown_list_item, resources.getStringArray(R.array.kepemilikan_rumah))
        val adapterPendidikanTerakhir = ArrayAdapter(requireActivity(), R.layout.dropdown_list_item, resources.getStringArray(R.array.pendidikan_terakhir))
        binding.actJenisKelamin.setAdapter(adapterJenisKelamin)
        binding.actKepemilikanRumah.setAdapter(adapterKepemilikanRumah)
        binding.actPendidikanTerakhir.setAdapter(adapterPendidikanTerakhir)
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

            if (valid) {
                val isRumahPribadi = kepemilikanRumah.toString() == "Rumah Pribadi"
                val model = ProfileResikoModel(
                    nama = models?.nama,
                    umur = models?.umur,
                    pendapatan = models?.pendapatan,
                    jenisKelamin = jenisKelamin.toString(),
                    pendidikanTerakhir = pendidikanTerakhir.toString(),
                    kepemilikanRumah = isRumahPribadi
                )
                viewModel.setProfileResiko(model)
                Toast.makeText(requireActivity(), "Berhasil!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object{
        const val EXTRA_MODEL = "extra_model"
    }
}