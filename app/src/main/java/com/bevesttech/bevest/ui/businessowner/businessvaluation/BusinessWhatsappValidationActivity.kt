package com.bevesttech.bevest.ui.businessowner.businessvaluation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bevesttech.bevest.databinding.ActivityBusinessWhatsappValidationBinding
import com.bevesttech.bevest.utils.ViewModelFactory
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.utils.BusinessValuationState
import com.bevesttech.bevest.utils.blockInput
import com.bevesttech.bevest.utils.disabled
import com.bevesttech.bevest.utils.enabled
import com.bevesttech.bevest.utils.gone
import com.bevesttech.bevest.utils.unblockInput
import com.bevesttech.bevest.utils.visible

class BusinessWhatsappValidationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBusinessWhatsappValidationBinding
    private val viewModel: BusinessValuationViewModel by viewModels { ViewModelFactory(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusinessWhatsappValidationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setObserver()
    }

    private fun setObserver() {
        viewModel.getUserSession().observe(this) { user ->
            viewModel.getBusinessOwnerByUID(user.uid ?: "").observe(this) { business ->
                with(binding) {
                    when (business) {
                        is Result.Loading -> {}
                        is Result.Success -> {
                            if (business.data != null) {
                               tvPhoneNumber.text = business.data.whatsappNumber
                            }

                            btnLanjut.setOnClickListener {
                                viewModel.updateValuationStatus(
                                    user.uid ?: "",
                                    BusinessValuationState.WAITING.name
                                )
                                    .observe(this@BusinessWhatsappValidationActivity) { valuationState ->
                                        when (valuationState) {
                                            is Result.Loading -> {
//                                                progressIndicator.visible()
                                                blockInput()
                                                btnLanjut.disabled()
                                            }

                                            is Result.Success -> {
//                                                progressIndicator.gone()
                                                unblockInput()
                                                btnLanjut.enabled()

                                                Intent(this@BusinessWhatsappValidationActivity, BusinessCallProcessActivity::class.java).also {
                                                    startActivity(it)
                                                    finish()
                                                }
                                            }

                                            is Result.Error -> {
                                                Toast.makeText(
                                                    this@BusinessWhatsappValidationActivity,
                                                    valuationState.error,
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                    }
                            }
                        }

                        is Result.Error -> {
                            business.error.let { message ->
                                 Toast.makeText(this@BusinessWhatsappValidationActivity, message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                }
            }

        }
    }
}