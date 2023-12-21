package com.bevesttech.bevest.ui.businessowner.businessvaluation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bevesttech.bevest.R
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.databinding.ActivityBusinessChangeWhatsappBinding
import com.bevesttech.bevest.utils.BusinessValuationState
import com.bevesttech.bevest.utils.ViewModelFactory
import com.bevesttech.bevest.utils.blockInput
import com.bevesttech.bevest.utils.disabled
import com.bevesttech.bevest.utils.enabled
import com.bevesttech.bevest.utils.gone
import com.bevesttech.bevest.utils.unblockInput
import com.bevesttech.bevest.utils.visible

class BusinessChangeWhatsappActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBusinessChangeWhatsappBinding
    private val businessValuationViewModel: BusinessValuationViewModel by viewModels { ViewModelFactory(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusinessChangeWhatsappBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setView()
        setObserver()
    }

    private fun setView() {
        with(binding) {
            viewModel = businessValuationViewModel
        }
    }

    private fun setObserver() {
        with(binding) {
            businessValuationViewModel.getUserSession().observe(this@BusinessChangeWhatsappActivity) { user ->
                btnLanjut.setOnClickListener {
                    businessValuationViewModel.whatsappNumber.observe(this@BusinessChangeWhatsappActivity) { whatsappNumber ->
                        if (whatsappNumber.isNullOrEmpty()) {
                            edtPhoneNumber.error = getString(R.string.tidak_boleh_kosong)
                        } else {
                            businessValuationViewModel.updateWhatsappNumber(user.uid ?: "", whatsappNumber)
                                .observe(this@BusinessChangeWhatsappActivity) { state ->
                                    when (state) {
                                        is Result.Loading -> {
                                        }

                                        is Result.Success -> {
                                            businessValuationViewModel.updateValuationStatus(
                                                user.uid ?: "",
                                                BusinessValuationState.WAITING.name
                                            )
                                                .observe(this@BusinessChangeWhatsappActivity) { valuationState ->
                                                    when (valuationState) {
                                                        is Result.Loading -> {
                                                            progressIndicator.visible()
                                                            blockInput()
                                                            btnLanjut.disabled()
                                                        }

                                                        is Result.Success -> {
                                                            progressIndicator.gone()
                                                            unblockInput()
                                                            btnLanjut.enabled()

                                                            Intent(this@BusinessChangeWhatsappActivity, BusinessCallProcessActivity::class.java).also {
                                                                startActivity(it)
                                                                finish()
                                                            }
                                                        }

                                                        is Result.Error -> {
                                                            Toast.makeText(
                                                                this@BusinessChangeWhatsappActivity,
                                                                valuationState.error,
                                                                Toast.LENGTH_SHORT
                                                            ).show()
                                                        }
                                                    }
                                                }
                                        }

                                        is Result.Error -> {

                                        }
                                    }
                                }
                        }
                    }
                }
            }
        }
    }
}