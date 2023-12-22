package com.bevesttech.bevest.data.repository

import androidx.lifecycle.LiveData
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.data.model.InvestorCore
import com.bevesttech.bevest.data.model.InvestorProfile
import com.bevesttech.bevest.data.source.remote.response.ProfilingResponse
import kotlinx.coroutines.flow.Flow

interface InvestorRepository {
    fun setInvestorProfileData(investorProfile: InvestorProfile): Flow<Result<Unit>>
    fun getInvestorProfile(): LiveData<Result<InvestorProfile?>>
    fun updateInvestorProfilingStatus(status: String): Flow<Result<Unit>>
    fun investorProfiling(investorProfile: InvestorProfile): LiveData<Result<ProfilingResponse>>
    fun setInvestorCoreData(investoreCore: InvestorCore): Flow<Result<Unit>>
}