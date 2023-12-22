package com.bevesttech.bevest.ui.investor.investorpersonaldata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bevesttech.bevest.R
import com.bevesttech.bevest.data.model.InvestorCore
import com.bevesttech.bevest.data.repository.InvestorRepository
import kotlinx.coroutines.Dispatchers

class InvestorPersonalDataViewModel(private val investorRepository: InvestorRepository) : ViewModel() {

    private val _detailKtpFormState = MutableLiveData<InvestorKtpDetailFormState>()
    val detailKtpFormState = _detailKtpFormState
    
    private val _bankAccountFormState = MutableLiveData<InvestorBankAccountFormState>()
    val bankAccountFormState = _bankAccountFormState

    private val _hasUploadKtp = MutableLiveData<Boolean>()
    val hasUploadKtp = _hasUploadKtp

    private val _bankName = MutableLiveData<String>()
    val bankName = _bankName

    private val _bankNumber = MutableLiveData<String>()
    val bankNumber = _bankNumber

    private val _nik = MutableLiveData<String>()
    val nik = _nik

    private val _placeOfBirth = MutableLiveData<String>()
    val placeOfBirth = _placeOfBirth

    private val _gender = MutableLiveData<String>()
    val gender = _gender

    private val _address = MutableLiveData<String>()
    val address = _address

    private val _rtRw = MutableLiveData<String>()
    val rtRw = _rtRw

    private val _religion = MutableLiveData<String>()
    val religion = _religion

    private val _maritalStatus = MutableLiveData<String>()
    val maritalStatus = _maritalStatus

    private val _job = MutableLiveData<String>()
    val job = _job

    private val _citizenship = MutableLiveData<String>()
    val citizenship = _citizenship
    
    init {
        _nik.value = ""
        _placeOfBirth.value = ""
        _gender.value = ""
        _address.value = ""
        _rtRw.value = ""
        _religion.value = ""
        _maritalStatus.value = ""
        _job.value = ""
        _citizenship.value = ""
        _bankName.value = ""
        _bankNumber.value = ""
        _hasUploadKtp.value = false
    }

    fun setHasUploadKtp(value: Boolean) {
        _hasUploadKtp.value = value
    }

    fun setBankName(text: CharSequence) {
        _bankName.value = text.toString()
    }

    fun setBankNumber(text: CharSequence) {
        _bankNumber.value = text.toString()
    }

    fun setNik(text: CharSequence) {
        _nik.value = text.toString()
    }

    fun setPlaceOfBirth(text: CharSequence) {
        _placeOfBirth.value = text.toString()
    }

    fun setGender(text: CharSequence) {
        _gender.value = text.toString()
    }

    fun setAddress(text: CharSequence) {
        _address.value = text.toString()
    }

    fun setRtRw(text: CharSequence) {
        _rtRw.value = text.toString()
    }

    fun setReligion(text: CharSequence) {
        _religion.value = text.toString()
    }

    fun setMaritalStatus(text: CharSequence) {
        _maritalStatus.value = text.toString()
    }

    fun setJob(text: CharSequence) {
        _job.value = text.toString()
    }

    fun setCitizenship(text: CharSequence) {
        _citizenship.value = text.toString()
    }

    fun validateKtpDetail() {
        if (_nik.value?.isEmpty() == true || (_nik.value?.length ?: 0) < 16) {
            _detailKtpFormState.value = InvestorKtpDetailFormState(nikError = R.string.tidak_boleh_kosong)
        } else if (_placeOfBirth.value?.isEmpty() == true) {
            _detailKtpFormState.value = InvestorKtpDetailFormState(placeOfBirthError = R.string.tidak_boleh_kosong)
        } else if (_gender.value?.isEmpty() == true) {
            _detailKtpFormState.value = InvestorKtpDetailFormState(genderError = R.string.tidak_boleh_kosong)
        } else if (_address.value?.isEmpty() == true) {
            _detailKtpFormState.value = InvestorKtpDetailFormState(addressError = R.string.tidak_boleh_kosong)
        } else if (_rtRw.value?.isEmpty() == true) {
            _detailKtpFormState.value = InvestorKtpDetailFormState(rtRwError = R.string.tidak_boleh_kosong)
        } else if (_religion.value?.isEmpty() == true) {
            _detailKtpFormState.value = InvestorKtpDetailFormState(religionError = R.string.tidak_boleh_kosong)
        } else if (_maritalStatus.value?.isEmpty() == true) {
            _detailKtpFormState.value = InvestorKtpDetailFormState(maritalStatusError = R.string.tidak_boleh_kosong)
        } else if (_job.value?.isEmpty() == true) {
            _detailKtpFormState.value = InvestorKtpDetailFormState(jobError = R.string.tidak_boleh_kosong)
        } else if (_citizenship.value?.isEmpty() == true) {
            _detailKtpFormState.value = InvestorKtpDetailFormState(citizenshipError = R.string.tidak_boleh_kosong)
        } else {
            _detailKtpFormState.value = InvestorKtpDetailFormState(isDataValid = true)
        }
    }

    fun validateBankAccount() {
        if (_bankName.value?.isEmpty() == true) {
            _bankAccountFormState.value = InvestorBankAccountFormState(bankNameError = R.string.tidak_boleh_kosong)
        } else if (_bankNumber.value?.isEmpty() == true) {
            _bankAccountFormState.value = InvestorBankAccountFormState(bankNumberError = R.string.tidak_boleh_kosong)
        } else {
            _bankAccountFormState.value = InvestorBankAccountFormState(isDataValid = true)
        }
    }

    fun setInvestorCoreData() = liveData(Dispatchers.IO) {
        val investorCoreData = InvestorCore(
            nik = nik.value,
            placeOfBirth = placeOfBirth.value,
            gender = gender.value,
            address = address.value,
            rtRw = rtRw.value,
            religion = religion.value,
            maritalStatus = maritalStatus.value,
            job = job.value,
            citizenship = citizenship.value,
            bankName = bankName.value,
            bankAccountNumber = bankNumber.value
        )

        investorRepository.setInvestorCoreData(investorCoreData).collect {
            emit(it)
        }
    }

}