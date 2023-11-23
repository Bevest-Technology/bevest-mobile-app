package com.bevesttech.bevest.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bevesttech.bevest.di.Injection
import com.bevesttech.bevest.ui.chooserole.ChooseRoleViewModel
import com.bevesttech.bevest.ui.login.LoginViewModel
import com.bevesttech.bevest.ui.register.RegisterViewModel

class ViewModelFactory(val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when(modelClass){
        LoginViewModel::class.java -> LoginViewModel(Injection.provideAuthRepository())
        RegisterViewModel::class.java -> RegisterViewModel(Injection.provideAuthRepository())
        ChooseRoleViewModel::class.java -> ChooseRoleViewModel(Injection.provideAuthRepository())
        else -> throw IllegalArgumentException("Unknown ViewModel class")
    } as T
}