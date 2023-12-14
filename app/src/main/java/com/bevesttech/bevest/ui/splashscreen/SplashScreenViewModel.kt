package com.bevesttech.bevest.ui.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bevesttech.bevest.data.repository.AuthRepository
import com.bevesttech.bevest.data.source.local.SessionPreference

class SplashScreenViewModel(private val sessionPreference: SessionPreference) : ViewModel() {
    fun isAlreadyOnboarding() = sessionPreference.isAlreadyOnboarding().asLiveData()

    fun isUserLoggedIn() = sessionPreference.isUserLoggedIn().asLiveData()

    fun isRoleAlreadySet() = sessionPreference.isRoleAlreadySet().asLiveData()
}