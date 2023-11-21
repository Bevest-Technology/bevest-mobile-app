package com.bevesttech.bevest.ui.chooserole

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChooseRoleViewModel:ViewModel() {
    private val _isInvestor = MutableLiveData<Boolean>()
    val isInvestor: LiveData<Boolean> = _isInvestor

    private val _isPemilikBisnis = MutableLiveData<Boolean>()
    val isPemilikBisnis: LiveData<Boolean> = _isPemilikBisnis

    fun setPemilikBisnis() {
        _isInvestor.value = false
        _isPemilikBisnis.value = true
    }

    fun setInvestor() {
        _isInvestor.value = true
        _isPemilikBisnis.value = false
    }
}