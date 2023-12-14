package com.bevesttech.bevest.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bevesttech.bevest.data.source.local.SessionPreference
import com.bevesttech.bevest.data.source.local.dataStore
import com.bevesttech.bevest.di.Injection
import com.bevesttech.bevest.ui.bisnislisting.BisnisListingViewModel
import com.bevesttech.bevest.ui.businessowner.ownerregistration.OwnerRegistrationViewModel
import com.bevesttech.bevest.ui.chooserole.ChooseRoleViewModel
import com.bevesttech.bevest.ui.forgotpassword.ForgotPasswordViewModel
import com.bevesttech.bevest.ui.login.LoginViewModel
import com.bevesttech.bevest.ui.onboarding.OnboardingViewModel
import com.bevesttech.bevest.ui.register.RegisterViewModel
import com.bevesttech.bevest.ui.splashscreen.SplashScreenViewModel

class ViewModelFactory(val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when(modelClass){
        SplashScreenViewModel::class.java -> SplashScreenViewModel(Injection.provideSessionPreferences(context))
        OnboardingViewModel::class.java -> OnboardingViewModel(Injection.provideSessionPreferences(context))
        LoginViewModel::class.java -> LoginViewModel(Injection.provideAuthRepository(context))
        RegisterViewModel::class.java -> RegisterViewModel(Injection.provideAuthRepository(context))
        ChooseRoleViewModel::class.java -> ChooseRoleViewModel(Injection.provideAuthRepository(context))
        ForgotPasswordViewModel::class.java -> ForgotPasswordViewModel(Injection.provideAuthRepository(context))
        BisnisListingViewModel::class.java -> BisnisListingViewModel(Injection.provideAuthRepository(context))
        OwnerRegistrationViewModel::class.java -> OwnerRegistrationViewModel()
        else -> throw IllegalArgumentException("Unknown ViewModel class")
    } as T
}