package com.bevesttech.bevest.ui.laporan

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.ActivityUpdateLaporanBinding
import com.bevesttech.bevest.ui.bisnislisting.BisnisListingViewModel
import com.bevesttech.bevest.utils.Utils
import com.bevesttech.bevest.utils.ViewModelFactory

class UpdateLaporanActivity : AppCompatActivity(), View.OnClickListener {

    private var _binding: ActivityUpdateLaporanBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LaporanKeuanganViewModel by viewModels { ViewModelFactory(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUpdateLaporanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isTahunan = intent.getBooleanExtra(EXTRA_IS_TAHUNAN, false)

        binding.topAppbar.apply {
            title = if (isTahunan) "Update Laporan Keuangan Tahunan" else  "Update Laporan Keuangan Bulanan"
            setNavigationOnClickListener { finish() }
        }
        setListener()
        setView()
    }

    private fun setListener() {
        binding.btnSimpan.setOnClickListener(this)
        binding.btnLaporanKeuanganNeraca.setOnClickListener(this)
        binding.btnLaporanKeuanganLabarugi.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object{
        const val EXTRA_IS_TAHUNAN = "extra_is_tahunan"
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_laporan_keuangan_neraca -> {
                launchLaporanNeraca.launch("application/pdf")
            }

            R.id.btn_laporan_keuangan_labarugi -> {
                launchLaporanLabaRugi.launch("application/pdf")
            }

            R.id.btn_simpan -> {
                if (checkField()) {
                    Toast.makeText(this, "Bisa disimpan", Toast.LENGTH_SHORT).show()
                    if (intent.getBooleanExtra(EXTRA_IS_TAHUNAN, false)) {

                    } else {

                    }
                    finish()
                }
            }
        }
    }

    private fun checkField(): Boolean {
        var isValid: Boolean
        with(binding) {
            if (edtTotalLabaBersih.text.isNullOrEmpty()) {
                edtTotalLabaBersih.error = getString(R.string.field_tidak_boleh_kosong)
                isValid = false
            }
            if (viewModel.laporanNeraca == null) {
                edtLaporanKeuanganNeraca.error = getString(R.string.field_tidak_boleh_kosong)
                isValid = false
            }
            if (viewModel.laporanLabaRugi == null) {
                edtLaporanKeuanganLabarugi.error = getString(R.string.field_tidak_boleh_kosong)
                isValid = false
            } else {
                isValid = true
            }
        }
        return isValid
    }

    private val launchLaporanNeraca = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ){ uri: Uri? ->
        if (uri != null) {
            viewModel.setLaporanNeraca(uri)
            setView()
        }
    }
    private val launchLaporanLabaRugi = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ){ uri: Uri? ->
        if (uri != null) {
            viewModel.setLaporanLabaRugi(uri)
            setView()
        }
    }

    private fun setView() {
        val laporanNeracaUri = viewModel.laporanNeraca
        val laporanLabaRugiUri = viewModel.laporanLabaRugi

        if (laporanNeracaUri != null) {
            binding.edtLaporanKeuanganNeraca.setText(
                Utils.getFileName(
                    laporanNeracaUri,
                    this
                )
            )
            binding.edtLaporanKeuanganNeraca.error = null
        }
        if (laporanLabaRugiUri != null) {
            binding.edtLaporanKeuanganLabarugi.setText(
                Utils.getFileName(
                    laporanLabaRugiUri,
                    this
                )
            )
            binding.edtLaporanKeuanganLabarugi.error = null
        }
    }
}