package com.bevesttech.bevest.ui.investor.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.ActivityInvestorHomeBinding
import com.bevesttech.bevest.ui.investor.register.InvestorRegisterActivity

class InvestorHomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInvestorHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInvestorHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLanjutIsiData.setOnClickListener {
            Intent(this, InvestorRegisterActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}