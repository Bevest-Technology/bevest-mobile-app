package com.bevesttech.bevest.data.repository

import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.data.model.BusinessOwner
import kotlinx.coroutines.flow.Flow

interface BusinessRepository {
    fun setOwnerRegistrationData(businessOwner: BusinessOwner): Flow<Result<Unit>>
}