package com.bevesttech.bevest.data.source.remote

import com.bevesttech.bevest.data.model.InvestorCore
import com.bevesttech.bevest.data.model.InvestorProfile
import com.bevesttech.bevest.data.source.InvestorDataSource
import com.bevesttech.bevest.utils.Constants
import com.bevesttech.bevest.utils.await
import com.google.firebase.firestore.FirebaseFirestore

class InvestorRemoteDataSource : InvestorDataSource {
    private val db = FirebaseFirestore.getInstance()
    private val investorProfileCollection = db.collection(Constants.INVESTOR_PROFILE_COL)
    private val investorCoreCollection = db.collection(Constants.INVESTOR_CORE_COL)

    override suspend fun setInvestorProfileData(uid: String, investorProfile: InvestorProfile) {
        investorProfileCollection.document(uid).set(investorProfile).await()
    }

    override suspend fun getInvestorProfileData(uid: String): InvestorProfile? {
        return investorProfileCollection.document(uid).get().await()
            .toObject(InvestorProfile::class.java)
    }

    override suspend fun updateInvestorProfilingStatus(uid: String, status: String) {
        investorProfileCollection.document(uid).update(Constants.INVESTOR_PROFILING_STATUS, status).await()
    }

    override suspend fun setInvestoreCoreData(uid: String, investorCore: InvestorCore) {
        investorCoreCollection.document(uid).set(investorCore).await()
    }
}