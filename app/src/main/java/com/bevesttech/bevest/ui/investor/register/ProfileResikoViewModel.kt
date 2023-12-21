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

    private var _tipeInvestor: String = "Progresif"
    val tipeInvestor get() = _tipeInvestor

    fun setTipeInvestor(tipeInvestor: String) {
        _tipeInvestor = tipeInvestor
    }

    private var _rekomendasiMenabung: String = "Rp.100.000 - RP. 300.000/Bulan"
    val rekomendasiMenabung get() = _rekomendasiMenabung

    fun setRekomendasiMenabung(rekomendasiMenabung: String) {
        _rekomendasiMenabung = rekomendasiMenabung
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