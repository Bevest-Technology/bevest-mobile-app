package com.bevesttech.bevest.ui.businessowner.businessscreening

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.ActivityBusinessScreeningRejectedBinding
import com.bevesttech.bevest.ui.businessowner.businessdataregistration.BusinessDataRegistrationActivity
import com.bevesttech.bevest.ui.comingsoon.ComingSoonActivity

class BusinessScreeningRejectedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBusinessScreeningRejectedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusinessScreeningRejectedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListener()
    }

    private fun setListener() {
        with(binding) {
            btnAjukanKembali.setOnClickListener {
                Intent(this@BusinessScreeningRejectedActivity, BusinessDataRegistrationActivity::class.java).also {
                    startActivity(it)
                }
            }

            btnBevestAcademy.setOnClickListener {
                Intent(this@BusinessScreeningRejectedActivity, ComingSoonActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }
}