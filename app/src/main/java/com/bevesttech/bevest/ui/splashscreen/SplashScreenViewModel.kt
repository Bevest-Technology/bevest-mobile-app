package com.bevesttech.bevest.ui.splashscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bevesttech.bevest.data.model.LoggedInUser
import com.bevesttech.bevest.data.repository.BusinessRepository
import com.bevesttech.bevest.data.repository.InvestorRepository
import com.bevesttech.bevest.data.source.local.SessionPreference

class SplashScreenViewModel(
    private val sessionPreference: SessionPreference,
    private val businessRepository: BusinessRepository,
    private val investorRepository: InvestorRepository
) : ViewModel() {
    fun isAlreadyOnboarding() = sessionPreference.isAlreadyOnboarding().asLiveData()

    fun getUserSession() = sessionPreference.getUserSession().asLiveData()

    fun isUserLoggedIn(user: LoggedInUser) = !user.uid.isNullOrEmpty()

    fun isRoleAlreadySet(user: LoggedInUser) = !user.role.isNullOrEmpty()

    fun isAlreadyOwnerRegistration(user: LoggedInUser) =
        businessRepository.getBusinessOwnerByUID(user.uid ?: "")



    fun isBusinessScreeningPassed(user: LoggedInUser) =
        businessRepository.getBusinessCoreDataByUID(user.uid ?: "")

}