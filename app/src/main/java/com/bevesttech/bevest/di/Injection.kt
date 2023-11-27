package com.bevesttech.bevest.di

import com.bevesttech.bevest.data.repository.AuthRepository
import com.bevesttech.bevest.data.repository.AuthRepositoryImpl
import com.bevesttech.bevest.data.source.remote.UserRemoteDataSource
import com.google.firebase.auth.FirebaseAuth

object Injection {

    fun provideAuthRepository() : AuthRepository {
        val firebaseAuth = FirebaseAuth.getInstance()
        val userRemoteDataSource = UserRemoteDataSource()
        return AuthRepositoryImpl.getInstance(firebaseAuth, userRemoteDataSource)
    }

}