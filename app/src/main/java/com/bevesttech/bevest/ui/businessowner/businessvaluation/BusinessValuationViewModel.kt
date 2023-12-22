package com.bevesttech.bevest.ui.businessowner.businessvaluation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.bevesttech.bevest.data.model.CoreBusiness
import com.bevesttech.bevest.data.repository.BusinessRepository
import com.bevesttech.bevest.data.source.local.SessionPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect

class BusinessValuationViewModel(
    private val sessionPreference: SessionPreference,
    private val businessRepository: BusinessRepository
) : ViewModel() {
    private val _whatsappNumber = MutableLiveData<String>()
    val whatsappNumber = _whatsappNumber

    init {
        _whatsappNumber.value = ""
    }

    fun setWhatsappNumber(number: CharSequence) {
        _whatsappNumber.value = number.toString()
    }

    fun getUserSession() = sessionPreference.getUserSession().asLiveData()
    fun getBusinessOwnerByUID(uid: String) = businessRepository.getBusinessOwnerByUID(uid)
    fun getBusinessCoreByUID(uid: String) = businessRepository.getBusinessCoreDataByUID(uid)
    fun updateWhatsappNumber(uid: String, whatsappNumber: String) = liveData(Dispatchers.IO) {
        businessRepository.updateWhatsappNumber(uid, whatsappNumber).collect {
            emit(it)
        }
    }
    fun updateValuationStatus(uid: String, status: String) = liveData(Dispatchers.IO) {
        businessRepository.updateValuationStatus(uid, status).collect {
            emit(it)
        }
    }

    fun businessValuation(business: CoreBusiness) = businessRepository.valuationBusiness(business)

    fun updateValuationValue(uid: String, valuationValue: String) = liveData(Dispatchers.IO) {
        businessRepository.updateValuationValue(uid, valuationValue).collect {
            emit(it)
        }
    }
}