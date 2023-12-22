package com.bevesttech.bevest.ui.businessowner.businessdataregistration

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.bevesttech.bevest.R
import com.bevesttech.bevest.data.model.CoreBusiness
import com.bevesttech.bevest.data.repository.BusinessRepository
import kotlinx.coroutines.Dispatchers

class BusinessDataRegistrationViewModel(private val businessRepository: BusinessRepository) :
    ViewModel() {

    private val _businessEntityDataFormState = MutableLiveData<BusinessEntityDataFormState>()
    val businessEntityDataFormState = _businessEntityDataFormState

    private val _businessGeneralDataFormState = MutableLiveData<BusinessGeneralDataFormState>()
    val businessGeneralDataFormState = _businessGeneralDataFormState

    private val _businessSalesValuationFromState =
        MutableLiveData<BusinessSalesValuationFormState>()
    val businessSalesValuationFormState = _businessSalesValuationFromState

    private val _businessDataOptionalFormState = MutableLiveData<BusinessDataOptionalFormState>()
    val businessDataOptionalFormState = _businessDataOptionalFormState

    private val _businessEntityName = MutableLiveData<String>()
    val businessEntityName = _businessEntityName

    private val _brandName = MutableLiveData<String>()
    val brandName = _brandName

    private val _businessCategory = MutableLiveData<String>()
    val businessCategory = _businessCategory

    private val _businessAddress = MutableLiveData<String>()
    val businessAddress = _businessAddress

    private val _brandPhoto = MutableLiveData<String>()
    val brandPhoto = _brandPhoto

    private val _mainProduct = MutableLiveData<String>()
    val mainProduct = _mainProduct

    private val _marketTarget = MutableLiveData<String>()
    val marketTarget = _marketTarget

    private val _legalEntityType = MutableLiveData<String>()
    val legalEntityType = _legalEntityType

    private val _salesSystemType = MutableLiveData<String>()
    val salesSystemType = _salesSystemType

    private val _averageAnnualSales = MutableLiveData<String>()
    val averageAnnualSales = _averageAnnualSales

    private val _assetTotal = MutableLiveData<String>()
    val assetTotal = _assetTotal

    private val _creditAssetCollateral = MutableLiveData<String>()
    val creditAssetCollateral = _creditAssetCollateral

    private val _employeesNumber = MutableLiveData<String>()
    val employeesNumber = _employeesNumber

    private val _creditDocumentNumber = MutableLiveData<String>()
    val creditDocumentNumber = _creditDocumentNumber

    private val _websiteUrl = MutableLiveData<String>()
    val websiteUrl = _websiteUrl

    private val _socialMedia = MutableLiveData<String>()
    val socialMedia = _socialMedia

    init {
        _businessEntityName.value = ""
        _brandName.value = ""
        _businessCategory.value = ""
        _businessAddress.value = ""
        _brandPhoto.value = ""
        _mainProduct.value = ""
        _marketTarget.value = ""
        _legalEntityType.value = ""
        _salesSystemType.value = ""
        _averageAnnualSales.value = ""
        _assetTotal.value = ""
        _creditAssetCollateral.value = ""
        _employeesNumber.value = ""
        _creditDocumentNumber.value = ""
        _websiteUrl.value = ""
        _socialMedia.value = ""
    }

    fun setBusinessEntityName(value: CharSequence) {
        _businessEntityName.value = value.toString()
    }

    fun setBrandName(value: CharSequence) {
        _brandName.value = value.toString()
    }

    fun setBusinessCategory(value: CharSequence) {
        _businessCategory.value = value.toString()
    }

    fun setBusinessAddress(value: CharSequence) {
        _businessAddress.value = value.toString()
    }

    fun setBrandPhoto(value: CharSequence) {
        _brandPhoto.value = value.toString()
    }

    fun setMainProduct(value: CharSequence) {
        _mainProduct.value = value.toString()
    }

    fun setMarketTarget(value: CharSequence) {
        _marketTarget.value = value.toString()
    }

    fun setLegalEntityType(value: CharSequence) {
        _legalEntityType.value = value.toString()
    }

    fun setSalesSystemType(value: CharSequence) {
        _salesSystemType.value = value.toString()
    }

    fun setAverageAnnualSales(value: CharSequence) {
        _averageAnnualSales.value = value.toString()
    }

    fun setAssetTotal(value: CharSequence) {
        _assetTotal.value = value.toString()
    }

    fun setCreditAssetCollateral(value: CharSequence) {
        _creditAssetCollateral.value = value.toString()
    }

    fun setEmployeesNumber(value: CharSequence) {
        _employeesNumber.value = value.toString()
    }

    fun setCreditDocumentNumber(value: CharSequence) {
        _creditDocumentNumber.value = value.toString()
    }

    fun setWebsiteUrl(value: CharSequence) {
        _websiteUrl.value = value.toString()
    }

    fun setSocialMedia(value: CharSequence) {
        _socialMedia.value = value.toString()
    }

    fun validateBusinessEntityData() {
        if (businessEntityName.value.isNullOrEmpty()) {
            _businessEntityDataFormState.value =
                BusinessEntityDataFormState(businessEntityNameError = R.string.tidak_boleh_kosong)
        } else if (brandName.value.isNullOrEmpty()) {
            _businessEntityDataFormState.value =
                BusinessEntityDataFormState(brandNameError = R.string.tidak_boleh_kosong)
        } else if (businessCategory.value.isNullOrEmpty()) {
            _businessEntityDataFormState.value =
                BusinessEntityDataFormState(businessCategoryError = R.string.tidak_boleh_kosong)
        } else if (businessAddress.value.isNullOrEmpty()) {
            _businessEntityDataFormState.value =
                BusinessEntityDataFormState(businessAddressError = R.string.tidak_boleh_kosong)
        } else {
            _businessEntityDataFormState.value = BusinessEntityDataFormState(isDataValid = true)
        }
    }

    fun validateBusinessGeneralData() {
        if (mainProduct.value.isNullOrEmpty()) {
            _businessGeneralDataFormState.value =
                BusinessGeneralDataFormState(mainProductError = R.string.tidak_boleh_kosong)
        } else if (marketTarget.value.isNullOrEmpty()) {
            _businessGeneralDataFormState.value =
                BusinessGeneralDataFormState(marketTargetError = R.string.tidak_boleh_kosong)
        } else if (legalEntityType.value.isNullOrEmpty()) {
            _businessGeneralDataFormState.value =
                BusinessGeneralDataFormState(legalEntityTypeError = R.string.tidak_boleh_kosong)
        } else if (salesSystemType.value.isNullOrEmpty()) {
            _businessGeneralDataFormState.value =
                BusinessGeneralDataFormState(salesSystemTypeError = R.string.tidak_boleh_kosong)
        } else {
            _businessGeneralDataFormState.value = BusinessGeneralDataFormState(isDataValid = true)
        }
    }

    fun validateBusinessSalesValuation() {
        if (averageAnnualSales.value.isNullOrEmpty()) {
            _businessSalesValuationFromState.value =
                BusinessSalesValuationFormState(averageAnnualSalesError = R.string.tidak_boleh_kosong)
        } else if (assetTotal.value.isNullOrEmpty()) {
            _businessSalesValuationFromState.value =
                BusinessSalesValuationFormState(assetTotalError = R.string.tidak_boleh_kosong)
        } else if (creditAssetCollateral.value.isNullOrEmpty()) {
            _businessSalesValuationFromState.value =
                BusinessSalesValuationFormState(creditAssetCollateralError = R.string.tidak_boleh_kosong)
        } else if (employeesNumber.value.isNullOrEmpty()) {
            _businessSalesValuationFromState.value =
                BusinessSalesValuationFormState(employeesNumberError = R.string.tidak_boleh_kosong)
        } else {
            _businessSalesValuationFromState.value =
                BusinessSalesValuationFormState(isDataValid = true)
        }
    }

    fun validateBusinessDataOptional() {
        if (creditDocumentNumber.value.isNullOrEmpty()) {
            _businessDataOptionalFormState.value =
                BusinessDataOptionalFormState(creditDocumentNumberError = R.string.tidak_boleh_kosong)
        } else {
            _businessDataOptionalFormState.value = BusinessDataOptionalFormState(isDataValid = true)
        }
    }

    fun setBusinessCoreData() = liveData(Dispatchers.IO) {
        val businessCoreData = CoreBusiness(
            businessEntityName = businessEntityName.value,
            brandName = brandName.value,
            businessCategory = businessCategory.value,
            businessAddress = businessAddress.value,
            brandPhoto = brandPhoto.value,
            mainProduct = mainProduct.value,
            marketTarget = marketTarget.value,
            legalEntityType = legalEntityType.value,
            salesSystemType = salesSystemType.value,
            averageAnnualSales = averageAnnualSales.value?.toDouble(),
            creditAssetCollateral = creditAssetCollateral.value?.toDouble(),
            assetTotal = assetTotal.value?.toDouble(),
            employeesNumber = employeesNumber.value?.toInt(),
            creditDocumentNumber = creditDocumentNumber.value?.toInt(),
            websiteUrl = websiteUrl.value,
            socialMedia = socialMedia.value
        )

        businessRepository.setBusinessCoreData(businessCoreData).collect { response ->
            emit(response)
        }
    }
}
