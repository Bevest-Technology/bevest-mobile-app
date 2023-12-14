package com.bevesttech.bevest.ui.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bevesttech.bevest.data.model.LoggedInUser
import com.bevesttech.bevest.data.repository.AuthRepository
import com.bevesttech.bevest.data.source.local.SessionPreference

class SplashScreenViewModel(private val sessionPreference: SessionPreference) : ViewModel() {
    fun isAlreadyOnboarding() = sessionPreference.isAlreadyOnboarding().asLiveData()

    fun getUserSession() = sessionPreference.getUserSession().asLiveData()

    fun isUserLoggedIn(user: LoggedInUser) = !user.uid.isNullOrEmpty()

    fun isRoleAlreadySet(user: LoggedInUser) = !user.role.isNullOrEmpty()
}