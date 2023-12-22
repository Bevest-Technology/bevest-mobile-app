package com.bevesttech.bevest.data.repository

import androidx.lifecycle.LiveData
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.data.model.BusinessOwner
import com.bevesttech.bevest.data.model.CoreBusiness
import com.bevesttech.bevest.data.source.remote.response.ScreeningResponse
import com.bevesttech.bevest.data.source.remote.response.ValuationResponse
import kotlinx.coroutines.flow.Flow

interface BusinessRepository {
    fun setOwnerRegistrationData(businessOwner: BusinessOwner): Flow<Result<Unit>>
    fun getBusinessOwnerByUID(uid: String): LiveData<Result<BusinessOwner?>>
    fun setBusinessCoreData(business: CoreBusiness): Flow<Result<Unit>>
    fun getBusinessCoreDataByUID(uid: String): LiveData<Result<CoreBusiness?>>
    fun screeningBusiness(business: CoreBusiness): LiveData<Result<ScreeningResponse>>
    fun updateScreeningStatus(uid: String, status: String): Flow<Result<Unit>>
    fun updateWhatsappNumber(uid: String, whatsappNumber: String): Flow<Result<Unit>>
    fun updateValuationStatus(uid: String, status: String): Flow<Result<Unit>>
    fun valuationBusiness(business: CoreBusiness): LiveData<Result<ValuationResponse>>
    fun updateValuationValue(uid: String, valuationValue: String): Flow<Result<Unit>>
}