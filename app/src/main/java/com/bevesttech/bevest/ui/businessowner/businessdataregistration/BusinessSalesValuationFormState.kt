package com.bevesttech.bevest.ui.businessowner.businessdataregistration

data class BusinessSalesValuationFormState(
    val averageAnnualSalesError: Int? = null,
    val creditAssetCollateralError: Int? = null,
    val assetTotalError: Int? = null,
    val employeesNumberError: Int? = null,
    val isDataValid: Boolean = false
)