package com.bevesttech.bevest.ui.bisnislisting

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.bevesttech.bevest.data.repository.AuthRepository
import com.bevesttech.bevest.utils.Utils.uriToFile

class BisnisListingViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private var _profileBisnis: ProfileBisnisModel = ProfileBisnisModel()
    val profileBisnis get() = _profileBisnis

    private var _fotoProfileUri: Uri? = null
    val fotoProfileUri get() = _fotoProfileUri

    private var _fotoBannerUri: Uri? = null
    val fotoBannerUri get() = _fotoBannerUri
    private var _fotoProductUri: Uri? = null
    val fotoProductUri get() = _fotoProductUri
    private var _videoUri: Uri? = null
    val videoUri get() = _videoUri

    fun setFotoProfileUri(uri: Uri) {
        _fotoProfileUri = uri
    }

    fun setFotoBannerUri(uri: Uri) {
        _fotoBannerUri = uri
    }

    fun setFotoProductUri(uri: Uri) {
        _fotoProductUri = uri
    }

    fun setVideoUri(uri: Uri) {
        _videoUri = uri
    }

    fun setProfileBisnis(
        deskripsi: String,
        profileOwner: String,
        visiMisi: String,
        alamat: String,
        totalCabang: Int,
        fotoProfile: Uri,
        fotoBanner: Uri,
        fotoProduct: Uri
    ) {
        val data = ProfileBisnisModel(
            deskripsi = deskripsi,
            profileOwner = profileOwner,
            visiMisi = visiMisi,
            alamat = alamat,
            totalCabang = totalCabang,
            fotoProfile = fotoProfile,
            fotoBanner = fotoBanner,
            fotoProduct = fotoProduct
        )
        _profileBisnis = data
    }
}

enum class Type { PRODUCT, PROFILE, BANNER, VIDEO }