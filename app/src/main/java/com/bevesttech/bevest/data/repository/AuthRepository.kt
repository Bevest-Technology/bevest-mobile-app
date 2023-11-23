package com.bevesttech.bevest.data.repository

import androidx.lifecycle.LiveData
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.data.model.LoggedInUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?
    fun login(email: String, password: String): LiveData<Result<FirebaseUser>>
    fun signup(name: String, email: String, password: String): LiveData<Result<LoggedInUser>>
    fun logout()
}