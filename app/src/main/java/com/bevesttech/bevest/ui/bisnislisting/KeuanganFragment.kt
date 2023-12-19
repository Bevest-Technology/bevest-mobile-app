package com.bevesttech.bevest.ui.bisnislisting

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentKeuanganBinding
import com.bevesttech.bevest.utils.Utils.getFileName
import com.bevesttech.bevest.utils.ViewModelFactory

class KeuanganFragment : Fragment(), View.OnClickListener {
    private var _binding: FragmentKeuanganBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BisnisListingViewModel by viewModels { ViewModelFactory(requireActivity()) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKeuanganBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListener()
        setView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_laporan_tahun_lalu -> {
                launchLaporanTahunLalu.launch("application/pdf")
            }

            R.id.btn_laporan_tahun_ini -> {
                launchLaporanTahunIni.launch("application/pdf")
            }

            R.id.btn_rencana_anggaran -> {
                launchRencanaAnggaran.launch("application/pdf")
            }

            R.id.btn_lanjut -> {
                checkField()
            }
        }
    }

    private fun checkField() {
        var isChecked = true
        with(binding) {
            if (edtTotalSaham.text.isNullOrEmpty()) {
                edtTotalSaham.error = getString(R.string.field_tidak_boleh_kosong)
                isChecked = false
            }
            if (edtTotalKeuntungan.text.isNullOrEmpty()) {
                edtTotalKeuntungan.error = getString(R.string.field_tidak_boleh_kosong)
                isChecked = false
            }
            if (edtKeuntunganSebelumnya.text.isNullOrEmpty()) {
                edtKeuntunganSebelumnya.error = getString(R.string.field_tidak_boleh_kosong)
                isChecked = false
            }
            if (edtBalikModal.text.isNullOrEmpty()) {
                edtBalikModal.error = getString(R.string.field_tidak_boleh_kosong)
                isChecked = false
            }
            if (viewModel.pdfLaporanTahunLaluUri == null) {
                edtLaporanTahunLalu.error = getString(R.string.field_tidak_boleh_kosong)
                isChecked = false
            }
            if (viewModel.pdfLaporanTahunIniUri == null) {
                edtLaporanTahunIni.error = getString(R.string.field_tidak_boleh_kosong)
                isChecked = false
            }
            if (viewModel.pdfRencanaAnggaranUri == null) {
                edtRencanaAnggaran.error = getString(R.string.field_tidak_boleh_kosong)
                isChecked = false
            }
            if (isChecked) {
                Toast.makeText(requireActivity(), "BERHASIL", Toast.LENGTH_SHORT).show()
                viewModel.setKeuangan(
                    totalSaham = edtTotalSaham.text.toString().toInt(),
                    totalKeuntungan = edtTotalKeuntungan.text.toString().toInt(),
                    keuntunganSebelumnya = edtKeuntunganSebelumnya.text.toString().toInt(),
                    perkiraanBalikModal = edtBalikModal.text.toString().toInt(),
                    laporanKeuanganTahunLalu = viewModel.pdfLaporanTahunLaluUri!!,
                    laporanKeuanganTahunIni = viewModel.pdfLaporanTahunIniUri!!,
                    rencanaAnggaran = viewModel.pdfRencanaAnggaranUri!!
                )
            }
        }
    }

    private fun setListener() {
        with(binding) {
            btnLaporanTahunLalu.setOnClickListener(this@KeuanganFragment)
            btnLaporanTahunIni.setOnClickListener(this@KeuanganFragment)
            btnRencanaAnggaran.setOnClickListener(this@KeuanganFragment)
            btnLanjut.setOnClickListener(this@KeuanganFragment)
        }
    }

    private fun setView() {
        val pdfTahunLaluUri = viewModel.pdfLaporanTahunLaluUri
        val pdfTahunIniUri = viewModel.pdfLaporanTahunIniUri
        val pdfRencanaAnggaran = viewModel.pdfRencanaAnggaranUri
        if (pdfTahunLaluUri != null) {
            binding.edtLaporanTahunLalu.setText(getFileName(pdfTahunLaluUri, requireActivity()))
            binding.edtLaporanTahunLalu.error = null
        }
        if (pdfTahunIniUri != null) {
            binding.edtLaporanTahunIni.setText(getFileName(pdfTahunIniUri, requireActivity()))
            binding.edtLaporanTahunIni.error = null
        }
        if (pdfRencanaAnggaran != null) {
            binding.edtRencanaAnggaran.setText(getFileName(pdfRencanaAnggaran, requireActivity()))
            binding.edtRencanaAnggaran.error = null
        }
    }

    private val launchLaporanTahunLalu = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ){ uri: Uri? ->
        if (uri != null) {
            viewModel.setPdfLaporanTahunLaluUri(uri)
            setView()
        }
    }

    private val launchLaporanTahunIni = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ){ uri: Uri? ->
        if (uri != null) {
            viewModel.setPdfLaporanTahunIniUri(uri)
            setView()
        }
    }

    private val launchRencanaAnggaran = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ){ uri: Uri? ->
        if (uri != null) {
            viewModel.setPdfRencanaAnggaranUri(uri)
            setView()
        }
    }

}