package com.bevesttech.bevest.ui.chooserole

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bevesttech.bevest.data.repository.AuthRepository
import com.bevesttech.bevest.utils.Role
import kotlinx.coroutines.Dispatchers

class ChooseRoleViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _roleChoosed = MutableLiveData<Role>()
    val roleChoosed: LiveData<Role> = _roleChoosed

    init {
        _roleChoosed.value = Role.NONE
    }

    fun updateUserRole(role: Role) = liveData(Dispatchers.IO) {
        authRepository.updateUserRole(role).collect { response ->
            emit(response)
        }
    }

    fun setPemilikBisnis() {
        _roleChoosed.value = Role.BUSINESS
    }

    fun setInvestor() {
        _roleChoosed.value = Role.INVESTOR
    }
}