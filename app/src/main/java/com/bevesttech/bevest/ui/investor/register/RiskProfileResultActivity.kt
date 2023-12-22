package com.bevesttech.bevest.ui.investor.register

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.databinding.ActivityRiskProfileResultBinding
import com.bevesttech.bevest.utils.ViewModelFactory
import com.bevesttech.bevest.utils.gone
import com.bevesttech.bevest.utils.visible

class RiskProfileResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRiskProfileResultBinding
    private val viewModel: ProfileResikoViewModel by viewModels { ViewModelFactory(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiskProfileResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setObserver()
    }

    private fun setObserver() {
        viewModel.getInvestorProfile().observe(this) { profile ->
            with(binding) {
                when (profile) {
                    is Result.Loading -> {
                        circularIndicator.visible()
                    }

                    is Result.Success -> {
                        if (profile.data?.profilingStatus != null) {

                        } else {
                            profile.data?.let {
                                viewModel.investorProfiling(it)
                                    .observe(this@RiskProfileResultActivity) { profiling ->
                                        when (profiling) {
                                            is Result.Loading -> {}
                                            is Result.Success -> {
                                                viewModel.updateInvestorProfilingStatus(
                                                    profiling.data.label.toString()
                                                        .replaceFirstChar(Char::uppercase)
                                                )
                                                    .observe(this@RiskProfileResultActivity) { updateState ->
                                                        when (updateState) {
                                                            is Result.Loading -> {}
                                                            is Result.Success -> {
                                                                circularIndicator.gone()

                                                                tvTipeInvestor.visible()
                                                                tvTipeInvestor.text = profiling.data.label.toString()
                                                                    .replaceFirstChar(Char::uppercase)
                                                            }

                                                            is Result.Error -> {}
                                                        }

                                                    }
                                            }

                                            is Result.Error -> {}
                                        }
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