package com.bevesttech.bevest.ui.businessowner.businessvaluation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.databinding.ActivityBusinessCallProcessBinding
import com.bevesttech.bevest.utils.ViewModelFactory

class BusinessCallProcessActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBusinessCallProcessBinding
    private val viewModel: BusinessValuationViewModel by viewModels { ViewModelFactory(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusinessCallProcessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setObserver()
    }

    private fun setObserver() {
        viewModel.getUserSession().observe(this@BusinessCallProcessActivity) { user ->
            viewModel.getBusinessCoreByUID(user.uid ?: "")
                .observe(this@BusinessCallProcessActivity) { businessCoreState ->
                    when (businessCoreState) {
                        is Result.Loading -> {}
                        is Result.Success -> {
                            businessCoreState.data?.let {
                                if (businessCoreState.data.businessValuation == null) {
                                    viewModel.businessValuation(it)
                                        .observe(this@BusinessCallProcessActivity) { valuationState ->
                                            when (valuationState) {
                                                is Result.Loading -> {}
                                                is Result.Success -> {
                                                    if (valuationState.data.valuation != null) {
                                                        viewModel.updateValuationValue(
                                                            user.uid ?: "",
                                                            valuationState.data.valuation.toString()
                                                        ).observe(this) { valuationValueState ->
                                                            when (valuationValueState) {
                                                                is Result.Loading -> {}
                                                                is Result.Success -> {

                                                                }

                                                                is Result.Error -> {}
                                                            }
                                                        }
                                                    } else {
                                                        Toast.makeText(
                                                            this@BusinessCallProcessActivity,
                                                            "Gagal",
                                                            Toast.LENGTH_SHORT
                                                        ).show()

                                                    }
                                                }

                                                is Result.Error -> {
                                                    Toast.makeText(
                                                        this@BusinessCallProcessActivity,
                                                        valuationState.error,
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            }
                                        }
                                } else {
                                    Toast.makeText(
                                        this@BusinessCallProcessActivity,
                                        "Sudah ada",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                }
                            }
                        }

                        is Result.Error -> {
                            Toast.makeText(
                                this@BusinessCallProcessActivity,
                                businessCoreState.error,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
        }
    }
}