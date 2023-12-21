package com.bevesttech.bevest.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.data.model.BusinessOwner
import com.bevesttech.bevest.data.model.CoreBusiness
import com.bevesttech.bevest.data.source.BusinessDataSource
import com.bevesttech.bevest.data.source.remote.response.ScreeningResponse
import com.bevesttech.bevest.data.source.remote.retrofit.ApiService
import com.bevesttech.bevest.utils.Utils.safeApiCall
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class BusinessRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val businessRemoteDataSource: BusinessDataSource,
    private val apiService: ApiService
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

    override fun getBusinessOwnerByUID(uid: String): LiveData<Result<BusinessOwner?>> = liveData {
        try {
            emit(Result.Loading)
            businessRemoteDataSource.getBusinessOwnerByUID(uid).also {
                emit(Result.Success(it))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun setBusinessCoreData(business: CoreBusiness) = flow {
        try {
            emit(Result.Loading)
            businessRemoteDataSource.setBusinessCoreData(
                firebaseAuth.uid.toString(),
                business
            ).also {
                emit(Result.Success(it))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun getBusinessCoreDataByUID(uid: String): LiveData<Result<CoreBusiness?>> = liveData {
        try {
            emit(Result.Loading)
            businessRemoteDataSource.getBusinessCoreDataByUID(uid).also {
                emit(Result.Success(it))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun screeningBusiness(business: CoreBusiness): LiveData<Result<ScreeningResponse>> =
        liveData {
            emit(Result.Loading)
            val jsonObject = JSONObject()
            jsonObject.put("total_aset", (business.assetTotal?.toInt() ?: 0) / 1000000)
            jsonObject.put("penjualan_rata2", (business.averageAnnualSales?.toInt() ?: 0) / 1000000)
            jsonObject.put("tenaga_kerja", business.employeesNumber ?: 0)
            jsonObject.put("aset_jaminan_kredit", (business.creditAssetCollateral?.toInt() ?: 0) / 1000000)
            jsonObject.put("jumlah_dokumen_kredit", business.creditDocumentNumber ?: 0)

            val jsonObjectString = jsonObject.toString()

            val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

            emit(safeApiCall {
                apiService.screening(
                    requestBody
                )
            })
        }

    override fun updateScreeningStatus(uid: String, status: String) = flow {
        try {
            emit(Result.Loading)
            Log.d("BusinessRepositoryImpl", "updateScreeningStatus: $status $uid")
            businessRemoteDataSource.updateScreeningStatus(uid, status).also {
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
            apiService: ApiService
        ): BusinessRepositoryImpl = instance ?: synchronized(this) {
            instance ?: BusinessRepositoryImpl(firebaseAuth, businessRemoteDataSource, apiService)
        }.also {
            instance = it
        }
    }
}