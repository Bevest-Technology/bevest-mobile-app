package com.bevesttech.bevest.ui.forgotpassword

import androidx.lifecycle.ViewModel
import com.bevesttech.bevest.data.repository.AuthRepository

class ForgotPasswordViewModel(private val authRepository: AuthRepository): ViewModel() {
    fun forgotPassword(email: String) = authRepository.forgotPassword(email)
}