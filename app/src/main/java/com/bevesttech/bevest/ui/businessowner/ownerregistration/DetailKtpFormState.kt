package com.bevesttech.bevest.ui.businessowner.ownerregistration

data class DetailKtpFormState(
    val nikError: Int? = null,
    val placeOfBirthError: Int? = null,
    val genderError: Int? = null,
    val addressError: Int? = null,
    val rtRwError: Int? = null,
    val religionError: Int? = null,
    val maritalStatusError: Int? = null,
    val jobError: Int? = null,
    val citizenshipError: Int? = null,
    val isDataValid: Boolean = false
)