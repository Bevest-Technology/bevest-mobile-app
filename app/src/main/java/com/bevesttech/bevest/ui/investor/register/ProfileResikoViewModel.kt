package com.bevesttech.bevest.ui.investor.register

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import kotlinx.parcelize.Parcelize

class ProfileResikoViewModel: ViewModel() {

    private var _profileResiko = ProfileResikoModel()
    val profileResiko get() = _profileResiko

    fun setProfileResiko(profileResikoModel: ProfileResikoModel) {
        _profileResiko = profileResikoModel
    }

}

@Parcelize
data class ProfileResikoModel(
    val nama: String? = null,
    val umur: Int? = null,
    val pendapatan: Int? = null,
    val jenisKelamin: String? = null,
    val pendidikanTerakhir: String? = null,
    val kepemilikanRumah: Boolean? = null
):Parcelable