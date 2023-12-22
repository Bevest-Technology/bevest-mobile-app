package com.bevesttech.bevest.ui.investor.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.data.model.InvestorProfile
import com.bevesttech.bevest.data.repository.InvestorRepository
import com.bevesttech.bevest.data.source.remote.response.ProfilingResponse
import kotlinx.coroutines.Dispatchers

class ProfileResikoViewModel(private val investorRepository: InvestorRepository) : ViewModel() {

    private val _investorProfile = MutableLiveData<InvestorProfile>()
    val investorProfile = _investorProfile

    private val _age = MutableLiveData<String>()
    val age = _age

    private val _gender = MutableLiveData<String>()
    val gender = _gender

    private val _income = MutableLiveData<String>()
    val income = _income

    private val _education = MutableLiveData<String>()
    val education = _education

    private val _maritalStatus = MutableLiveData<String>()
    val maritalStatus = _maritalStatus

    private val _numberOfChildren = MutableLiveData<String>()
    val numberOfChildren = _numberOfChildren

    private val _homeOwnership = MutableLiveData<String>()
    val homeOwnership = _homeOwnership

    init {

    }

    fun setAge(text: CharSequence) {
        _age.value = text.toString()
    }

    fun setGender(text: CharSequence) {
        _gender.value = text.toString()
    }

    fun setIncome(text: CharSequence) {
        _income.value = text.toString()
    }

    fun setEducation(text: CharSequence) {
        _education.value = text.toString()
    }

    fun setMaritalStatus(text: CharSequence) {
        _maritalStatus.value = text.toString()
    }

    fun setNumberOfChildren(text: CharSequence) {
        _numberOfChildren.value = text.toString()
    }

    fun setHomeOwnership(text: CharSequence) {
        _homeOwnership.value = text.toString()
    }

    fun setInvestorProfilingData() = liveData(Dispatchers.IO) {
        val genderEncoding = when (gender.value) {
            "Laki-Laki" -> 0
            else -> 1
        }

        val educationEncoding = when (education.value) {
            "D3" -> 1
            "S1" -> 2
            "S2" -> 3
            "S3" -> 4
            else -> 0
        }

        val maritalStatusEncoding = when (maritalStatus.value) {
            "Kawin" -> 1
            else -> 0
        }

        val homeOwnershipEncoding = when (homeOwnership.value) {
            "Menyewa" -> 0
            else -> 1
        }

        val profilingData = InvestorProfile(
            age = age.value?.toInt() ?: 0,
            gender = genderEncoding,
            income = income.value?.toDouble() ?: 0.0,
            maritalStatus = maritalStatusEncoding,
            education = educationEncoding,
            numberOfChildren = numberOfChildren.value?.toInt() ?: 0,
            homeOwnership = homeOwnershipEncoding
        )

        _investorProfile

        investorRepository.setInvestorProfileData(profilingData).collect {
            emit(it)
        }
    }

    fun getInvestorProfile() = investorRepository.getInvestorProfile()

    fun investorProfiling(investorProfile: InvestorProfile) = investorRepository.investorProfiling(investorProfile)

    fun updateInvestorProfilingStatus(status: String) = liveData(Dispatchers.IO) {
        investorRepository.updateInvestorProfilingStatus(status).collect {
            emit(it)
        }
    }
}
