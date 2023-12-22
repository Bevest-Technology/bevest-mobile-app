package com.bevesttech.bevest.ui.investor.investorpersonaldata

data class InvestorBankAccountFormState(
    val bankNameError: Int? = null,
    val bankNumberError: Int? = null,
    val isDataValid: Boolean = false
)