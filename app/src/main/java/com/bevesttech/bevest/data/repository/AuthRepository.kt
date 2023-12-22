package com.bevesttech.bevest.data.repository

import androidx.lifecycle.LiveData
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.data.model.LoggedInUser
import com.bevesttech.bevest.utils.Role
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    var currentUser: LoggedInUser?
    fun login(email: String, password: String): LiveData<Result<LoggedInUser>>
    fun loginWithGoogle(idToken: String): LiveData<Result<LoggedInUser>>
    fun signup(name: String, email: String, password: String): LiveData<Result<LoggedInUser>>
    fun updateUserRole(role: Role): Flow<Result<Unit>>
    suspend fun logout()
    fun forgotPassword(email: String): LiveData<Result<String>>

}