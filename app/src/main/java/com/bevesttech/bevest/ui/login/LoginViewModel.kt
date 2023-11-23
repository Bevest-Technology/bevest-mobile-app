package com.bevesttech.bevest.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bevesttech.bevest.R
import com.bevesttech.bevest.data.repository.AuthRepository
import com.bevesttech.bevest.utils.Utils

class LoginViewModel(private val userRepository: AuthRepository) : ViewModel() {

    private val _loginFormState = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginFormState

    fun login(email: String, password: String) = userRepository.login(email, password)

    fun loginDataChanged(email: String, password: String) {
        if (!Utils.isValidEmail(email)) {
            _loginFormState.value = LoginFormState(emailError = R.string.email_tidak_valid)
        } else if (!Utils.isValidPassword(password)) {
            _loginFormState.value = LoginFormState(passwordError = R.string.kata_sandi_minimal)
        } else {
            _loginFormState.value = LoginFormState(isDataValid = true)
        }
    }

    fun logout() = userRepository.logout()
}