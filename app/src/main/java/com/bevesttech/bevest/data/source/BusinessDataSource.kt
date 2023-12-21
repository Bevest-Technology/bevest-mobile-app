package com.bevesttech.bevest.data.source

import com.bevesttech.bevest.data.model.BusinessOwner
import com.bevesttech.bevest.data.model.CoreBusiness

interface BusinessDataSource {
    suspend fun setbusinessOwnerData(uid: String, businessOwner: BusinessOwner)
    suspend fun getBusinessOwnerByUID(uid: String): BusinessOwner?
    suspend fun setBusinessCoreData(uid: String, businessOwner: CoreBusiness)
    suspend fun getBusinessCoreDataByUID(uid: String): CoreBusiness?
    suspend fun updateScreeningStatus(uid: String, status: String)
}