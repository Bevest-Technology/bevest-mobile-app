package com.bevesttech.bevest.ui.businessowner.businessdataregistration

data class BusinessEntityDataFormState(
    val businessEntityNameError: Int? = null,
    val brandNameError: Int? = null,
    val businessCategoryError: Int? = null,
    val businessAddressError: Int? = null,
    val brandPhotoError: Int? = null,
    val isDataValid: Boolean = false
)