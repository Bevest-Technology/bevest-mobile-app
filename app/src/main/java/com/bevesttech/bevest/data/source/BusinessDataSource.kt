package com.bevesttech.bevest.data.source

import com.bevesttech.bevest.data.model.BusinessOwner

interface BusinessDataSource {
    suspend fun setbusinessOwnerData(uid: String, businessOwner: BusinessOwner)
    suspend fun getBusinessOwnerByUID(uid: String): BusinessOwner?
}