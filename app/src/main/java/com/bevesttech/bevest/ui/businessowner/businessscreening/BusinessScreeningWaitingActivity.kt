package com.bevesttech.bevest.ui.businessowner.businessscreening

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.databinding.ActivityBusinessScreeingWaitingBinding
import com.bevesttech.bevest.utils.ViewModelFactory

class BusinessScreeningWaitingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBusinessScreeingWaitingBinding
    private val viewModel: BusinessScreeningViewModel by viewModels { ViewModelFactory(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusinessScreeingWaitingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setObserver()
    }

    private fun setObserver() {
        viewModel.getUserSession().observe(this) { user ->
            viewModel.getBusinessCoreDataByUID(user.uid ?: "").observe(this) { business ->
                when (business) {
                    is Result.Loading -> {}
                    is Result.Success -> {
                        if (business.data != null) {
                            viewModel.businessScreening(business.data).observe(this) { screening ->
                                when (screening) {
                                    is Result.Loading -> {}
                                    is Result.Success -> {
                                        if (screening.data.label == "Layak" || screening.data.label == "layak") {
                                            viewModel.updateScreeningStatus(user.uid ?: "", "Layak")
                                                .observe(this) {
                                                    when (it) {
                                                        is Result.Loading -> {}
                                                        is Result.Success -> {
                                                            Intent(
                                                                this,
                                                                BusinessScreeningAcceptedActivity::class.java
                                                            ).also {
                                                                startActivity(it)
                                                                finish()
                                                            }
                                                        }

                                                        is Result.Error -> {}
                                                    }
                                                }

                                        } else {
                                            viewModel.updateScreeningStatus(
                                                user.uid ?: "",
                                                "Tidak Layak"
                                            ).observe(this) {
                                                when (it) {
                                                    is Result.Loading -> {}
                                                    is Result.Success -> {
                                                        Intent(
                                                            this,
                                                            BusinessScreeningRejectedActivity::class.java
                                                        ).also {
                                                            startActivity(it)
                                                            finish()
                                                        }
                                                    }

                                                    is Result.Error -> {}
                                                }
                                            }
                                        }
                                    }

                                    is Result.Error -> {
                                        Toast.makeText(this, screening.error, Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                }
                            }
                        }
                    }

                    is Result.Error -> {
                        Toast.makeText(this, business.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}