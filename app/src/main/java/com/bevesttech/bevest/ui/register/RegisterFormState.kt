package com.bevesttech.bevest.ui.register

data class RegisterFormState(
    val nameError: Int? = null,
    val emailError: Int? = null,
    val passwordError: Int? = null,
    val confPasswordError: Int? = null,
    val isDataValid: Boolean = false
)