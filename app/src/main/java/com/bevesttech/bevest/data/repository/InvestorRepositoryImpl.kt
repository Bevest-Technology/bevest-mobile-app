package com.bevesttech.bevest.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.data.model.InvestorProfile
import com.bevesttech.bevest.data.source.remote.InvestorRemoteDataSource
import com.bevesttech.bevest.data.source.remote.response.ProfilingResponse
import com.bevesttech.bevest.data.source.remote.retrofit.ApiService
import com.bevesttech.bevest.utils.Utils.safeApiCall
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class InvestorRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val investorRemoteDataSource: InvestorRemoteDataSource,
    private val apiService: ApiService
) : InvestorRepository {
    override fun setInvestorProfileData(investorProfile: InvestorProfile) = flow {
        try {
            emit(Result.Loading)
            investorRemoteDataSource.setInvestorProfileData(
                firebaseAuth.uid.toString(),
                investorProfile
            ).also {
                emit(Result.Success(it))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun getInvestorProfile(): LiveData<Result<InvestorProfile?>> = liveData {
        try {
            emit(Result.Loading)
            investorRemoteDataSource.getInvestorProfileData(firebaseAuth.uid.toString()).also {
                emit(Result.Success(it))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun updateInvestorProfilingStatus(status: String): Flow<Result<Unit>> = flow {
        try {
            emit(Result.Loading)
            investorRemoteDataSource.updateInvestorProfilingStatus(
                firebaseAuth.uid.toString(),
                status
            ).also {
                emit(Result.Success(it))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun investorProfiling(investorProfile: InvestorProfile): LiveData<Result<ProfilingResponse>> =
        liveData {
            emit(Result.Loading)
            val jsonObject = JSONObject()
            jsonObject.put("age", investorProfile.age)
            jsonObject.put("gender", investorProfile.gender)
            jsonObject.put("income", investorProfile.income)
            jsonObject.put("education", investorProfile.education)
            jsonObject.put("marital_status", investorProfile.maritalStatus)
            jsonObject.put("number_of_children", investorProfile.numberOfChildren)
            jsonObject.put("home_ownership", investorProfile.homeOwnership)

            val jsonObjectString = jsonObject.toString()

            val requestBody =
                jsonObjectString.toRequestBody(contentType = "application/json".toMediaTypeOrNull())

            emit(safeApiCall {
                apiService.profiling(requestBody)
            })
        }

    companion object {
        @Volatile
        private var instance: InvestorRepositoryImpl? = null

        fun getInstance(
            firebaseAuth: FirebaseAuth,
            investorRemoteDataSource: InvestorRemoteDataSource,
            apiService: ApiService
        ): InvestorRepositoryImpl = instance ?: synchronized(this) {
            instance ?: InvestorRepositoryImpl(firebaseAuth, investorRemoteDataSource, apiService)
        }.also {
            instance = it
        }
    }

}