package com.bevesttech.bevest.ui.businessowner.ownerregistration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OwnerRegistrationViewModel: ViewModel() {

    // -1 belum diatur, 0 belum, 1 sudah
    private val _haveBusinessEntity = MutableLiveData<Int>()
    val haveBusinessEntity = _haveBusinessEntity

    init {
        _haveBusinessEntity.value = -1
    }

    fun setHaveBusinessEntity(value: Int) {
        _haveBusinessEntity.value = value
    }

}