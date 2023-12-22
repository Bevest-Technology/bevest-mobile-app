package com.bevesttech.bevest.ui.businessowner.ownerregistration

data class OwnerPersonalIdentityFormState (
    val fullNameError: Int? = null,
    val whatsappError: Int? = null,
    val fullAddressError: Int? = null,
    val photoError: Int? = null,
    val isDataValid: Boolean = false
)