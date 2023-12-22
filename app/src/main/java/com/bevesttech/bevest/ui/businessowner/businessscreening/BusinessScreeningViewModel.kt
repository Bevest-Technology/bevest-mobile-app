package com.bevesttech.bevest.ui.businessowner.businessscreening

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.bevesttech.bevest.data.model.CoreBusiness
import com.bevesttech.bevest.data.repository.BusinessRepository
import com.bevesttech.bevest.data.source.local.SessionPreference
import kotlinx.coroutines.Dispatchers

class BusinessScreeningViewModel(private val sessionPreference: SessionPreference,  private val businessRepository: BusinessRepository) : ViewModel() {
    fun getUserSession() = sessionPreference.getUserSession().asLiveData()
    fun getBusinessCoreDataByUID(uid: String) = businessRepository.getBusinessCoreDataByUID(uid)
    fun businessScreening(business: CoreBusiness) = businessRepository.screeningBusiness(business)
    fun updateScreeningStatus(uid: String, status: String) = liveData(Dispatchers.IO) {
        Log.d("BusinessScreening", "updateScreeningStatus: $uid, $status")
        businessRepository.updateScreeningStatus(uid, status).collect { response ->
            emit(response)
        }
    }
}