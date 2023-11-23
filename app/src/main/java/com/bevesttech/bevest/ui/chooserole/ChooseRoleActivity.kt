package com.bevesttech.bevest.ui.chooserole

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bevesttech.bevest.MainActivity
import com.bevesttech.bevest.R
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.databinding.ActivityChooseRoleBinding
import com.bevesttech.bevest.utils.Role
import com.bevesttech.bevest.utils.ViewModelFactory
import com.bevesttech.bevest.utils.blockInput
import com.bevesttech.bevest.utils.disabled
import com.bevesttech.bevest.utils.enabled
import com.bevesttech.bevest.utils.gone
import com.bevesttech.bevest.utils.setupAppBar
import com.bevesttech.bevest.utils.unblockInput
import com.bevesttech.bevest.utils.visible

class ChooseRoleActivity : AppCompatActivity() {
    private var _binding: ActivityChooseRoleBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ChooseRoleViewModel by viewModels { ViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityChooseRoleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setView()
        setObserver()
        setListener()
    }

    private fun setView() {
        with(binding) {
            setupAppBar(binding.topAppbar, getString(R.string.pilih_role))


        }
    }

    private fun setListener() {
        binding.apply {
            chooseInvestor.setOnClickListener { viewModel.setInvestor() }
            choosePemilikBisnis.setOnClickListener { viewModel.setPemilikBisnis() }
        }
    }

    private fun setObserver() {
        viewModel.roleChoosed.observe(this@ChooseRoleActivity) { role ->
            with(binding) {
                btnLanjut.isEnabled = !role.equals(Role.NONE)

                when (role) {
                    Role.BUSINESS -> {
                        chooseInvestor.isChecked = true
                    }

                    Role.INVESTOR -> {
                        chooseInvestor.isChecked = true
                    }

                    Role.NONE -> {

                    }
                }

                btnLanjut.setOnClickListener {
                    viewModel.updateUserRole(role).observe(this@ChooseRoleActivity) { result ->
                        when (result) {
                            is Result.Loading -> {
                                progressIndicator.visible()
                                blockInput()
                                btnLanjut.disabled()
                            }

                            is Result.Success -> {
                                progressIndicator.gone()
                                unblockInput()
                                Intent(
                                    this@ChooseRoleActivity,
                                    MainActivity::class.java
                                ).also {
                                    startActivity(it)
                                }
                            }

                            is Result.Error -> {
                                progressIndicator.gone()
                                unblockInput()
                                btnLanjut.enabled()
                                Toast.makeText(
                                    this@ChooseRoleActivity,
                                    result.error.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}