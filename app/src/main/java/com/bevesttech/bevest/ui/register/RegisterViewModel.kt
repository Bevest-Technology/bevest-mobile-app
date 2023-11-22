package com.bevesttech.bevest.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bevesttech.bevest.R
import com.bevesttech.bevest.utils.Utils

class RegisterViewModel : ViewModel() {

    private val _registerFormState = MutableLiveData<RegisterFormState>()
    val registerFormState: LiveData<RegisterFormState> = _registerFormState

    fun registerDataChanged(name: String, email: String, password: String, confPassword: String) {
        if (name.length < 3) {
            _registerFormState.value = RegisterFormState(nameError = R.string.nama_lengkap_error_min_3)
        } else if (!Utils.isValidEmail(email)) {
            _registerFormState.value = RegisterFormState(emailError = R.string.email_tidak_valid)
        } else if (!Utils.isValidPassword(password)) {
            _registerFormState.value = RegisterFormState(passwordError = R.string.kata_sandi_minimal)
        } else if (confPassword != password) {
            _registerFormState.value = RegisterFormState(confPasswordError = R.string.kata_sandi_tidak_sama)
        } else {
            _registerFormState.value = RegisterFormState(isDataValid = true)
        }
    }

}