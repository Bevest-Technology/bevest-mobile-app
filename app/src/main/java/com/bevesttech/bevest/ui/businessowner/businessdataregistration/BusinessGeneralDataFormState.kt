package com.bevesttech.bevest.ui.businessowner.businessdataregistration

data class BusinessGeneralDataFormState(
    val mainProductError: Int? = null,
    val marketTargetError: Int? = null,
    val legalEntityTypeError: Int? = null,
    val salesSystemTypeError: Int? = null,
    val isDataValid: Boolean = false
)
