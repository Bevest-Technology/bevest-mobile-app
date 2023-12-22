package com.bevesttech.bevest.ui.businessowner.businessscreening

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.ActivityBusinessScreeningAcceptedBinding
import com.bevesttech.bevest.ui.businessowner.businessvaluation.BusinessWhatsappValidationActivity

class BusinessScreeningAcceptedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBusinessScreeningAcceptedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusinessScreeningAcceptedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListener()
    }

    private fun setListener() {
        binding.btnLanjutWawancara.setOnClickListener {
            Intent(this, BusinessWhatsappValidationActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}