package com.bevesttech.bevest.ui.chooserole

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bevesttech.bevest.databinding.ActivityChooseRoleBinding

class ChooseRoleActivity : AppCompatActivity() {
    private var _binding: ActivityChooseRoleBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityChooseRoleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[ChooseRoleViewModel::class.java]

        viewModel.isInvestor.observe(this){isInvestor ->
            binding.chooseInvestor.isChecked = isInvestor
        }
        viewModel.isPemilikBisnis.observe(this){isPemilikBisnis ->
            binding.choosePemilikBisnis.isChecked = isPemilikBisnis
        }
        binding.apply {
            chooseInvestor.setOnClickListener{ viewModel.setInvestor() }
            choosePemilikBisnis.setOnClickListener{ viewModel.setPemilikBisnis() }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}