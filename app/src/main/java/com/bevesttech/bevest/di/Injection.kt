package com.bevesttech.bevest.di

import android.content.Context
import com.bevesttech.bevest.data.repository.AuthRepository
import com.bevesttech.bevest.data.repository.AuthRepositoryImpl
import com.bevesttech.bevest.data.repository.BusinessRepository
import com.bevesttech.bevest.data.repository.BusinessRepositoryImpl
import com.bevesttech.bevest.data.source.local.SessionPreference
import com.bevesttech.bevest.data.source.local.dataStore
import com.bevesttech.bevest.data.source.remote.BusinessRemoteDataSource
import com.bevesttech.bevest.data.source.remote.UserRemoteDataSource
import com.google.firebase.auth.FirebaseAuth

object Injection {

    fun provideAuthRepository(context: Context): AuthRepository {
        val sessionPreference = SessionPreference.getInstance(context.dataStore)
        val firebaseAuth = FirebaseAuth.getInstance()
        val userRemoteDataSource = UserRemoteDataSource()
        return AuthRepositoryImpl.getInstance(firebaseAuth, userRemoteDataSource, sessionPreference)
    }

    fun provideSessionPreferences(context: Context): SessionPreference {
        return SessionPreference.getInstance(context.dataStore)
    }

    fun provideBusinessRepository(context: Context): BusinessRepository {
        val firebaseAuth = FirebaseAuth.getInstance()
        val businessRemoteDataSource = BusinessRemoteDataSource()
        return BusinessRepositoryImpl.getInstance(firebaseAuth, businessRemoteDataSource)
    }

}