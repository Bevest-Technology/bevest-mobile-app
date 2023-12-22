package com.bevesttech.bevest.data.source

import com.bevesttech.bevest.data.model.InvestorCore
import com.bevesttech.bevest.data.model.InvestorProfile

interface InvestorDataSource {
    suspend fun setInvestorProfileData(uid: String, investorProfile: InvestorProfile)
    suspend fun getInvestorProfileData(uid: String): InvestorProfile?
    suspend fun updateInvestorProfilingStatus(uid: String, status: String)
    suspend fun setInvestoreCoreData(uid: String, investorCore: InvestorCore)
}