package com.bevesttech.bevest.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bevesttech.bevest.data.source.local.SessionPreference
import kotlinx.coroutines.launch

class OnboardingViewModel(private val sessionPreference: SessionPreference) : ViewModel() {
    fun saveOnboardingSession() {
        viewModelScope.launch {
            sessionPreference.saveOnboardingSession()
        }
    }
}