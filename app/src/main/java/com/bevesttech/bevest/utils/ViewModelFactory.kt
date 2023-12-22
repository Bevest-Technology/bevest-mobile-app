package com.bevesttech.bevest.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bevesttech.bevest.di.Injection
import com.bevesttech.bevest.ui.bisnislisting.BisnisListingViewModel
import com.bevesttech.bevest.ui.businessowner.businessdataregistration.BusinessDataRegistrationViewModel
import com.bevesttech.bevest.ui.businessowner.businessscreening.BusinessScreeningViewModel
import com.bevesttech.bevest.ui.businessowner.businessvaluation.BusinessValuationViewModel
import com.bevesttech.bevest.ui.businessowner.ownerregistration.OwnerRegistrationViewModel
import com.bevesttech.bevest.ui.chooserole.ChooseRoleViewModel
import com.bevesttech.bevest.ui.forgotpassword.ForgotPasswordViewModel
import com.bevesttech.bevest.ui.investor.register.ProfileResikoViewModel
import com.bevesttech.bevest.ui.laporan.LaporanKeuanganViewModel
import com.bevesttech.bevest.ui.login.LoginViewModel
import com.bevesttech.bevest.ui.onboarding.OnboardingViewModel
import com.bevesttech.bevest.ui.register.RegisterViewModel
import com.bevesttech.bevest.ui.splashscreen.SplashScreenViewModel

class ViewModelFactory(val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        SplashScreenViewModel::class.java -> SplashScreenViewModel(
            Injection.provideSessionPreferences(
                context
            ),
            Injection.provideBusinessRepository(context),
            Injection.provideInvestorRepository(context)
        )

        OnboardingViewModel::class.java -> OnboardingViewModel(
            Injection.provideSessionPreferences(
                context
            )
        )

        LoginViewModel::class.java -> LoginViewModel(Injection.provideAuthRepository(context))
        RegisterViewModel::class.java -> RegisterViewModel(
            Injection.provideAuthRepository(
                context
            )
        )

        ChooseRoleViewModel::class.java -> ChooseRoleViewModel(
            Injection.provideAuthRepository(
                context
            )
        )

        ForgotPasswordViewModel::class.java -> ForgotPasswordViewModel(
            Injection.provideAuthRepository(
                context
            )
        )

        BisnisListingViewModel::class.java -> BisnisListingViewModel(
            Injection.provideAuthRepository(
                context
            )
        )

        OwnerRegistrationViewModel::class.java -> OwnerRegistrationViewModel(
            Injection.provideAuthRepository(
                context
            ), Injection.provideBusinessRepository(context)
        )

        BusinessDataRegistrationViewModel::class.java -> BusinessDataRegistrationViewModel(
            Injection.provideBusinessRepository(context)
        )

        BusinessScreeningViewModel::class.java -> BusinessScreeningViewModel(
            Injection.provideSessionPreferences(
                context
            ),
            Injection.provideBusinessRepository(context)
        )

        BusinessValuationViewModel::class.java -> BusinessValuationViewModel(
            Injection.provideSessionPreferences(
                context
            ),
            Injection.provideBusinessRepository(context)
        )

        LaporanKeuanganViewModel::class.java -> LaporanKeuanganViewModel(
            Injection.provideAuthRepository(
                context
            )
        )

        ProfileResikoViewModel::class.java -> ProfileResikoViewModel(
            Injection.provideInvestorRepository(
                context
            )
        )

        else -> throw IllegalArgumentException("Unknown ViewModel class")
    } as T
}