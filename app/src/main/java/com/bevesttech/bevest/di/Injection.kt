package com.bevesttech.bevest.di

import android.content.Context
import android.util.Log
import com.bevesttech.bevest.data.repository.AuthRepository
import com.bevesttech.bevest.data.repository.AuthRepositoryImpl
import com.bevesttech.bevest.data.source.local.SessionPreference
import com.bevesttech.bevest.data.source.local.dataStore
import com.bevesttech.bevest.data.source.remote.UserRemoteDataSource
import com.bevesttech.bevest.ui.splashscreen.SplashScreenActivity
import com.google.firebase.auth.FirebaseAuth

object Injection {

    fun provideAuthRepository(context: Context) : AuthRepository {
        val sessionPreference = SessionPreference.getInstance(context.dataStore)
        val firebaseAuth = FirebaseAuth.getInstance()
        val userRemoteDataSource = UserRemoteDataSource()
        return AuthRepositoryImpl.getInstance(firebaseAuth, userRemoteDataSource, sessionPreference)
    }

    fun provideSessionPreferences(context: Context) : SessionPreference {
        return SessionPreference.getInstance(context.dataStore)
    }

}