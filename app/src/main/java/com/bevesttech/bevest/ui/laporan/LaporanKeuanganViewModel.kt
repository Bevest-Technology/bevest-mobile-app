package com.bevesttech.bevest.ui.laporan

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.bevesttech.bevest.data.repository.AuthRepository

class LaporanKeuanganViewModel(authRepository: AuthRepository): ViewModel() {
    private var _laporanNeraca: Uri? = null
    val laporanNeraca get() = _laporanNeraca
    private var _laporanLabaRugi: Uri? = null
    val laporanLabaRugi get() = _laporanLabaRugi

    fun setLaporanNeraca(uri: Uri) {
        _laporanNeraca = uri
    }

    fun setLaporanLabaRugi(uri: Uri) {
        _laporanLabaRugi = uri
    }
}