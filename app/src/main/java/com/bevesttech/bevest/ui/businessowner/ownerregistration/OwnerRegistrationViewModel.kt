package com.bevesttech.bevest.ui.businessowner.ownerregistration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bevesttech.bevest.R
import com.bevesttech.bevest.data.model.BusinessOwner
import com.bevesttech.bevest.data.repository.AuthRepository
import com.bevesttech.bevest.data.repository.BusinessRepository
import kotlinx.coroutines.Dispatchers

class OwnerRegistrationViewModel(private val authRepository: AuthRepository, private val businessRepository: BusinessRepository) : ViewModel() {

    private val _ownerPersonalIdentityFormState = MutableLiveData<OwnerPersonalIdentityFormState>()
    val ownerPersonalIdentityFormState = _ownerPersonalIdentityFormState

    private val _detailKtpFormState = MutableLiveData<DetailKtpFormState>()
    val detailKtpFormState = _detailKtpFormState

    private val _ownerBusiness = MutableLiveData<BusinessOwner>()
    val ownerBusiness = _ownerBusiness

    private val _haveBusinessEntity = MutableLiveData<Boolean>()
    val haveBusinessEntity = _haveBusinessEntity

    private val _fullName = MutableLiveData<String>()
    val fullName = _fullName

    private val _whatsappNumber = MutableLiveData<String>()
    val whatsappNumber = _whatsappNumber

    private val _fullAddress = MutableLiveData<String>()
    val fullAddress = _fullAddress

    private val _photo = MutableLiveData<String>()
    val photo = _photo

    private val _hasUploadKtp = MutableLiveData<Boolean>()
    val hasUploadKtp = _hasUploadKtp

    private val _ktpPhoto = MutableLiveData<String>()
    val ktpPhoto = _ktpPhoto

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
        _haveBusinessEntity.value = false
        _fullName.value = ""
        _whatsappNumber.value = ""
        _fullAddress.value = ""
        _hasUploadKtp.value = false
        _nik.value = ""
        _placeOfBirth.value = ""
        _gender.value = ""
        _address.value = ""
        _rtRw.value = ""
        _religion.value = ""
        _maritalStatus.value = ""
        _job.value = ""
        _citizenship.value = ""
    }

    fun setHaveBusinessEntity(value: Boolean) {
        _haveBusinessEntity.value = value
    }

    fun setFullName(text: CharSequence) {
        _fullName.value = text.toString()
    }

    fun setWhatsappNumber(text: CharSequence) {
        _whatsappNumber.value = text.toString()
    }

    fun setFullAddress(text: CharSequence) {
        _fullAddress.value = text.toString()
    }

    fun setHasUploadKtp(value: Boolean) {
        _hasUploadKtp.value = value
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

    fun validateOwnerPersonalIdentity() {
        if (_fullName.value?.isEmpty() == true) {
            _ownerPersonalIdentityFormState.value =
                OwnerPersonalIdentityFormState(fullNameError = R.string.nama_tidak_boleh_kosong)
        } else if (_whatsappNumber.value?.isEmpty() == true || (_whatsappNumber.value?.length
                ?: 0) < 10
        ) {
            _ownerPersonalIdentityFormState.value =
                OwnerPersonalIdentityFormState(whatsappError = R.string.nomor_whatsapp_tidak_boleh_kosong)
        } else if (_fullAddress.value?.isEmpty() == true) {
            _ownerPersonalIdentityFormState.value =
                OwnerPersonalIdentityFormState(fullAddressError = R.string.alamat_tidak_boleh_kosong)
        } else {
            _ownerPersonalIdentityFormState.value =
                OwnerPersonalIdentityFormState(isDataValid = true)
        }
    }

    fun validateKtpDetail() {
        if (_nik.value?.isEmpty() == true || (_nik.value?.length ?: 0) < 16) {
            _detailKtpFormState.value = DetailKtpFormState(nikError = R.string.tidak_boleh_kosong)
        } else if (_placeOfBirth.value?.isEmpty() == true) {
            _detailKtpFormState.value = DetailKtpFormState(placeOfBirthError = R.string.tidak_boleh_kosong)
        } else if (_gender.value?.isEmpty() == true) {
            _detailKtpFormState.value = DetailKtpFormState(genderError = R.string.tidak_boleh_kosong)
        } else if (_address.value?.isEmpty() == true) {
            _detailKtpFormState.value = DetailKtpFormState(addressError = R.string.tidak_boleh_kosong)
        } else if (_rtRw.value?.isEmpty() == true) {
            _detailKtpFormState.value = DetailKtpFormState(rtRwError = R.string.tidak_boleh_kosong)
        } else if (_religion.value?.isEmpty() == true) {
            _detailKtpFormState.value = DetailKtpFormState(religionError = R.string.tidak_boleh_kosong)
        } else if (_maritalStatus.value?.isEmpty() == true) {
            _detailKtpFormState.value = DetailKtpFormState(maritalStatusError = R.string.tidak_boleh_kosong)
        } else if (_job.value?.isEmpty() == true) {
            _detailKtpFormState.value = DetailKtpFormState(jobError = R.string.tidak_boleh_kosong)
        } else if (_citizenship.value?.isEmpty() == true) {
            _detailKtpFormState.value = DetailKtpFormState(citizenshipError = R.string.tidak_boleh_kosong)
        } else {
            _detailKtpFormState.value = DetailKtpFormState(isDataValid = true)
        }
    }

    fun setOwnerRegistrationData() = liveData(Dispatchers.IO) {
        val businessOwnerData = BusinessOwner(
            haveBusinessEntity = haveBusinessEntity.value,
            fullName = fullName.value,
            whatsappNumber = whatsappNumber.value,
            fullAddress = fullAddress.value,
            photo = "",
            ktpPhoto = "",
            nik = nik.value,
            placeOfBirth = placeOfBirth.value,
            gender = gender.value,
            address = address.value,
            rtRw = rtRw.value,
            religion = religion.value,
            maritalStatus = maritalStatus.value,
            job = job.value,
            citizenship = citizenship.value
        )

        businessRepository.setOwnerRegistrationData(businessOwnerData).collect { response ->
            emit(response)
        }
    }
}