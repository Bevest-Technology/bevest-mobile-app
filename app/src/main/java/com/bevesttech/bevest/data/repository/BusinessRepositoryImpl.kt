package com.bevesttech.bevest.data.repository

import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.data.model.BusinessOwner
import com.bevesttech.bevest.data.source.BusinessDataSource
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.flow

class BusinessRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val businessRemoteDataSource: BusinessDataSource
) : BusinessRepository {
    override fun setOwnerRegistrationData(businessOwner: BusinessOwner) = flow {
        try {
            emit(Result.Loading)
            businessRemoteDataSource.setbusinessOwnerData(
                firebaseAuth.uid.toString(),
                businessOwner
            ).also {
                emit(Result.Success(it))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: BusinessRepositoryImpl? = null

        fun getInstance(
            firebaseAuth: FirebaseAuth,
            businessRemoteDataSource: BusinessDataSource,
        ): BusinessRepositoryImpl = instance ?: synchronized(this) {
            instance ?: BusinessRepositoryImpl(firebaseAuth, businessRemoteDataSource)
        }.also {
            instance = it
        }
    }
}