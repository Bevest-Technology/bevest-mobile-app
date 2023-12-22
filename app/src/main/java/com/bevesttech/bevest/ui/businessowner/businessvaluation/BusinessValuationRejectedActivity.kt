package com.bevesttech.bevest.ui.businessowner.businessvaluation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.ActivityBusinessValuationRejectedBinding
import com.bevesttech.bevest.ui.businessowner.businessdataregistration.BusinessDataRegistrationActivity
import com.bevesttech.bevest.ui.comingsoon.ComingSoonActivity

class BusinessValuationRejectedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBusinessValuationRejectedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusinessValuationRejectedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListener()
    }

    private fun setListener() {
        with(binding) {
            btnAjukanKembali.setOnClickListener {
                Intent(this@BusinessValuationRejectedActivity, BusinessDataRegistrationActivity::class.java).also {
                    startActivity(it)
                }
            }

            btnBevestAcademy.setOnClickListener {
                Intent(this@BusinessValuationRejectedActivity, ComingSoonActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }
}