package com.bevesttech.bevest.data.source.remote

import android.util.Log
import com.bevesttech.bevest.data.model.BusinessOwner
import com.bevesttech.bevest.data.model.CoreBusiness
import com.bevesttech.bevest.data.source.BusinessDataSource
import com.bevesttech.bevest.utils.Constants
import com.bevesttech.bevest.utils.await
import com.google.firebase.firestore.FirebaseFirestore

class BusinessRemoteDataSource : BusinessDataSource {
    private val db = FirebaseFirestore.getInstance()
    private val businessOwnerCollection = db.collection(Constants.BUSINESS_OWNER_COL)
    private val businessCoreCollection = db.collection(Constants.BUSINESS_CORE_COL)

    override suspend fun setbusinessOwnerData(uid: String, businessOwner: BusinessOwner) {
        businessOwnerCollection.document(uid).set(businessOwner).await()
    }

    override suspend fun getBusinessOwnerByUID(uid: String): BusinessOwner? {
        return businessOwnerCollection.document(uid).get().await()
            .toObject(BusinessOwner::class.java)
    }

    override suspend fun setBusinessCoreData(uid: String, businessOwner: CoreBusiness) {
        businessCoreCollection.document(uid).set(businessOwner).await()
    }

    override suspend fun getBusinessCoreDataByUID(uid: String): CoreBusiness? {
        return businessCoreCollection.document(uid).get().await().toObject(CoreBusiness::class.java)
    }

    override suspend fun updateScreeningStatus(uid: String, status: String) {
        Log.d("BusinessRemoteDataSource", "updateScreeningStatus: $status $uid")
        businessCoreCollection.document(uid)
            .update(Constants.BUSINESS_SCREENING_STATUS_FIELD, status).await()
    }

    override suspend fun updateWhatsappNumber(uid: String, whatsappNumber: String) {
        businessOwnerCollection.document(uid)
            .update(Constants.WHATSAPP_NUMBER_FIELD, whatsappNumber).await()
    }

    override suspend fun updateValuationStatus(uid: String, status: String) {
        businessCoreCollection.document(uid)
            .update(Constants.BUSINESS_VALUATION_STATUS_FIELD, status).await()
    }

    override suspend fun updateValuationValue(uid: String, valuationValue: String) {
        businessCoreCollection.document(uid)
            .update(Constants.BUSINESS_VALUATION_FIELD, valuationValue.toDouble()).await()
    }

}