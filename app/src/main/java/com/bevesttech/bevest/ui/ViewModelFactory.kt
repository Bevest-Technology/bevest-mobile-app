package com.bevesttech.bevest.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bevesttech.bevest.ui.login.LoginViewModel

class ViewModelFactory(val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when(modelClass){
        LoginViewModel::class.java -> LoginViewModel()
        else -> throw IllegalArgumentException("Unknown ViewModel class")
    } as T
}