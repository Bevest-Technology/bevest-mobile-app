package com.bevesttech.bevest.ui.bisnislisting

import android.net.Uri

data class ProfileBisnisModel(
    val deskripsi: String? = null,
    val profileOwner: String? = null,
    val visiMisi: String? = null,
    val alamat: String? = null,
    val totalCabang: Int? = null,
    val fotoProfile: Uri? = null,
    val fotoBanner: Uri? = null,
    val fotoProduct: Uri? = null,
    val video: Uri? = null,
)
