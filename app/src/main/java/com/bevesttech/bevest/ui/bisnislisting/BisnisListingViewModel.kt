package com.bevesttech.bevest.ui.bisnislisting

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.bevesttech.bevest.data.repository.AuthRepository
import com.bevesttech.bevest.utils.Utils.uriToFile

class BisnisListingViewModel(private val authRepository: AuthRepository) : ViewModel() {

    //Profile Bisnis

    private var _profileBisnis: ProfileBisnisModel = ProfileBisnisModel()
    val profileBisnis get() = _profileBisnis

    private var _fotoProfileUri: Uri? = null
    val fotoProfileUri get() = _fotoProfileUri

    private var _fotoBannerUri: Uri? = null
    val fotoBannerUri get() = _fotoBannerUri
    private var _fotoProductUri: Uri? = null
    val fotoProductUri get() = _fotoProductUri

    fun setFotoProfileUri(uri: Uri) {
        _fotoProfileUri = uri
    }

    fun setFotoBannerUri(uri: Uri) {
        _fotoBannerUri = uri
    }

    fun setFotoProductUri(uri: Uri) {
        _fotoProductUri = uri
    }

    fun setProfileBisnis(
        deskripsi: String,
        profileOwner: String,
        visiMisi: String,
        alamat: String,
        totalCabang: Int,
        fotoProfile: Uri,
        fotoBanner: Uri,
        fotoProduct: Uri,
        video: String? = null
    ) {
        val data = ProfileBisnisModel(
            deskripsi = deskripsi,
            profileOwner = profileOwner,
            visiMisi = visiMisi,
            alamat = alamat,
            totalCabang = totalCabang,
            fotoProfile = fotoProfile,
            fotoBanner = fotoBanner,
            fotoProduct = fotoProduct,
            video = video
        )
        _profileBisnis = data
    }

    //Keuangan

    private var _keuangan: KeuanganModel = KeuanganModel()
    val keuangan get() = _keuangan

    private var _pdfLaporanTahunIniUri: Uri? = null
    val pdfLaporanTahunIniUri get() = _pdfLaporanTahunIniUri

    private var _pdfLaporanTahunLaluUri: Uri? = null
    val pdfLaporanTahunLaluUri get() = _pdfLaporanTahunLaluUri

    private var _pdfRencanaAnggaranUri: Uri? = null
    val pdfRencanaAnggaranUri get() = _pdfRencanaAnggaranUri
    fun setPdfLaporanTahunIniUri(uri: Uri) {
        _pdfLaporanTahunIniUri = uri
    }
    fun setPdfLaporanTahunLaluUri(uri: Uri) {
        _pdfLaporanTahunLaluUri = uri
    }
    fun setPdfRencanaAnggaranUri(uri: Uri) {
        _pdfRencanaAnggaranUri = uri
    }

    fun setKeuangan(
        totalSaham: Int,
        totalKeuntungan: Int,
        keuntunganSebelumnya: Int,
        perkiraanBalikModal: Int,
        laporanKeuanganTahunLalu: Uri,
        laporanKeuanganTahunIni: Uri,
        rencanaAnggaran: Uri,
    ){
        val data = KeuanganModel(
            totalSaham,
            totalKeuntungan,
            keuntunganSebelumnya,
            perkiraanBalikModal,
            laporanKeuanganTahunLalu,
            laporanKeuanganTahunIni,
            rencanaAnggaran
        )
        _keuangan = data
    }

}

enum class Type { PRODUCT, PROFILE, BANNER }