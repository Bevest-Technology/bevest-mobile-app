package com.bevesttech.bevest.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.bevesttech.bevest.MainActivity
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.ActivitySplashScreenBinding
import com.bevesttech.bevest.ui.chooserole.ChooseRoleActivity
import com.bevesttech.bevest.ui.login.LoginActivity
import com.bevesttech.bevest.ui.onboarding.OnboardingActivity
import com.bevesttech.bevest.utils.ViewModelFactory

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var  binding: ActivitySplashScreenBinding
    private val viewModel: SplashScreenViewModel by viewModels { ViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setObserver()
    }

    private fun setObserver() {
        viewModel.isAlreadyOnboarding().observe(this) { onboarding ->
            if (!onboarding) {
                Intent(this, OnboardingActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            } else {
                viewModel.isUserLoggedIn().observe(this) { userLoggedIn ->
                    if (userLoggedIn) {
                        viewModel.isRoleAlreadySet().observe(this) { roleChosen ->
                            if (roleChosen) {
                                Intent(this, MainActivity::class.java).also {
                                    startActivity(it)
                                    finish()
                                }
                            } else {
                                Intent(this, ChooseRoleActivity::class.java).also {
                                    startActivity(it)
                                    finish()
                                }
                            }
                        }
                    } else {
                        Intent(this, LoginActivity::class.java).also {
                            startActivity(it)
                            finish()
                        }
                    }
                }
            }
        }
    }
}