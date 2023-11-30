package com.bevesttech.bevest.ui.bisnislisting

import android.net.Uri

data class KeuanganModel(
    val totalSaham: Int? = null,
    val totalKeuntungan: Int? = null,
    val keuntunganSebelumnya: Int? = null,
    val perkiraanBalikModal: Int? = null,
    val laporanKeuanganTahunLalu: Uri? = null,
    val laporanKeuanganTahunIni: Uri? = null,
    val rencanaAnggaran: Uri? = null,
    )
