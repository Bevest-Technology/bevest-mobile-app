package com.bevesttech.bevest.ui.businessowner.businessvaluation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.databinding.ActivityBusinessValuationAcceptedBinding
import com.bevesttech.bevest.utils.Utils.toIDRCurrenty
import com.bevesttech.bevest.utils.ViewModelFactory

class BusinessValuationAcceptedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBusinessValuationAcceptedBinding
    private val viewModel: BusinessValuationViewModel by viewModels { ViewModelFactory(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusinessValuationAcceptedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setObserver()
    }

    private fun setObserver() {
        viewModel.getUserSession().observe(this) { user ->
            viewModel.getBusinessCoreByUID(user.uid ?: "").observe(this) { businessCore ->
                when(businessCore) {
                    is Result.Loading -> {}
                    is Result.Success -> {
                        with(binding) {
                            businessCore.data?.let { data ->
                                if (data.businessValuation != null) {
                                    tvTotalValuasi.text = data.businessValuation.toString().toIDRCurrenty()
                                } else {
                                    tvTotalValuasi.text = "0".toIDRCurrenty()
                                }
                            }
                        }
                    }
                    is Result.Error -> {}
                }
            }

        }
    }
}