package com.bevesttech.bevest.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.databinding.ActivitySplashScreenBinding
import com.bevesttech.bevest.ui.businessowner.businessdataregistration.BusinessDataRegistrationActivity
import com.bevesttech.bevest.ui.businessowner.businessscreening.BusinessScreeningRejectedActivity
import com.bevesttech.bevest.ui.businessowner.businessscreening.BusinessScreeningWaitingActivity
import com.bevesttech.bevest.ui.businessowner.businessvaluation.BusinessCallProcessActivity
import com.bevesttech.bevest.ui.businessowner.businessvaluation.BusinessValuationAcceptedActivity
import com.bevesttech.bevest.ui.businessowner.ownerregistration.OwnerRegistrationActivity
import com.bevesttech.bevest.ui.chooserole.ChooseRoleActivity
import com.bevesttech.bevest.ui.login.LoginActivity
import com.bevesttech.bevest.ui.onboarding.OnboardingActivity
import com.bevesttech.bevest.utils.BusinessValuationState
import com.bevesttech.bevest.utils.Utils
import com.bevesttech.bevest.utils.ViewModelFactory

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
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
                viewModel.getUserSession().observe(this) { user ->
                    if (viewModel.isUserLoggedIn(user)) {
                        if (viewModel.isRoleAlreadySet(user)) {

                            if (Utils.isBusiness(user.role ?: "")) {
                                viewModel.isAlreadyOwnerRegistration(user).observe(this) { state ->
                                    when (state) {
                                        is Result.Loading -> {

                                        }

                                        is Result.Success -> {
                                            state.data?.let {
                                                viewModel.isBusinessScreeningPassed(user)
                                                    .observe(this) { screening ->
                                                        when (screening) {
                                                            is Result.Loading -> {

                                                            }

                                                            is Result.Success -> {
                                                                screening.data?.let {
                                                                    if (screening.data.businessValuationStatus == BusinessValuationState.WAITING.name) {
                                                                        Intent(
                                                                            this,
                                                                            BusinessCallProcessActivity::class.java
                                                                        ).also {
                                                                            startActivity(it)
                                                                            finish()
                                                                        }
                                                                    } else if (screening.data.businessValuationStatus == BusinessValuationState.ACCEPTED.name) {
                                                                        Intent(this, BusinessValuationAcceptedActivity::class.java).also {
                                                                            startActivity(it)
                                                                            finish()
                                                                        }
                                                                    } else if (screening.data.businessValuationStatus == BusinessValuationState.REJECTED.name) {
                                                                        Intent(this, BusinessScreeningRejectedActivity::class.java).also {
                                                                            startActivity(it)
                                                                            finish()
                                                                        }
                                                                    } else {
                                                                        Intent(
                                                                            this,
                                                                            BusinessScreeningWaitingActivity::class.java
                                                                        ).also {
                                                                            startActivity(it)
                                                                            finish()
                                                                        }
                                                                    }
                                                                }

                                                                if (screening.data == null) {
                                                                    Intent(
                                                                        this,
                                                                        BusinessDataRegistrationActivity::class.java
                                                                    ).also {
                                                                        startActivity(it)
                                                                        finish()
                                                                    }
                                                                }
                                                            }

                                                            is Result.Error -> {
//                                                            Intent(this, MainActivity::class.java).also {
//                                                                startActivity(it)
//                                                                finish()
//                                                            }
                                                            }
                                                        }
                                                    }
                                            }
                                            if (state.data == null) {
                                                Intent(
                                                    this,
                                                    OwnerRegistrationActivity::class.java
                                                ).also {
                                                    startActivity(it)
                                                    finish()
                                                }
                                            }
                                        }

                                        is Result.Error -> {
//                                            Intent(this, MainActivity::class.java).also {
//                                                startActivity(it)
//                                                finish()
//                                            }
                                        }
                                    }

//                                viewModel

//                                    } else {
//                                    Intent(this, MainActivity::class.java).also {
//                                        startActivity(it)
//                                        finish()
//                                    }
                                }
                            } else {
                                Intent(this, ChooseRoleActivity::class.java).also {
                                    startActivity(it)
                                    finish()
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
}