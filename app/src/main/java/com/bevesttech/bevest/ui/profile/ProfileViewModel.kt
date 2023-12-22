package com.bevesttech.bevest.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bevesttech.bevest.data.repository.AuthRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val authRepository: AuthRepository): ViewModel() {
    fun logout() = viewModelScope.launch { authRepository.logout() }
}