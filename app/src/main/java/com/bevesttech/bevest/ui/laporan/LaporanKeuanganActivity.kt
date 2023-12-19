package com.bevesttech.bevest.ui.laporan

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.ActivityLaporanKeuanganBinding
import com.bevesttech.bevest.ui.business.BusinessListAdapter
import com.bevesttech.bevest.ui.business.ListItemModel

class LaporanKeuanganActivity : AppCompatActivity() {
    private var _binding: ActivityLaporanKeuanganBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLaporanKeuanganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = mutableListOf<ListItemModel>()
        list.add(ListItemModel("Update Laporan Keuangan", R.drawable.ic_note_pencil))
        list.add(ListItemModel("Lihat Laporan Keuangan", R.drawable.ic_clipboardtext))
        
        showMenu(list)

        binding.topAppbar.setNavigationOnClickListener {
            finish()
        }
    }



    private fun showMenu(list: List<ListItemModel>) {
        val rv = binding.rvListMenu
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)
        val adapter = BusinessListAdapter(list)
        rv.adapter = adapter

        adapter.setOnItemClickCallback(object : BusinessListAdapter.OnItemClickCallback {
            override fun onItemClicked(position: Int) {
                if (position == 0) {
                    showBottomDialog(DialogType.UPDATE)
                } else {
                    showBottomDialog(DialogType.LIHAT)
                }
            }
        })
    }

    private fun showBottomDialog(dialogType: DialogType) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_sheet_layout)

        val laporanTahunan = dialog.findViewById<RelativeLayout>(R.id.laporan_tahunan_layout)
        val laporanBulanan = dialog.findViewById<RelativeLayout>(R.id.laporan_bulanan_layout)

        when (dialogType) {
            DialogType.UPDATE -> {
                laporanTahunan.setOnClickListener {
                    Intent(this, UpdateLaporanActivity::class.java).also {
                        it.putExtra(UpdateLaporanActivity.EXTRA_IS_TAHUNAN, true)
                        startActivity(it)
                    }
                    dialog.hide()
                }
                laporanBulanan.setOnClickListener {
                    Intent(this, UpdateLaporanActivity::class.java).also {
                        it.putExtra(UpdateLaporanActivity.EXTRA_IS_TAHUNAN, false)
                        startActivity(it)
                    }
                    dialog.hide()
                }
            }

            DialogType.LIHAT -> {
                laporanTahunan.setOnClickListener {
                    Toast.makeText(this, "Lihat Laporan Tahunan", Toast.LENGTH_SHORT).show()
                }
                laporanBulanan.setOnClickListener {
                    Toast.makeText(this, "Lihat Laporan Bulanan", Toast.LENGTH_SHORT).show()
                }
            }

        }


        dialog.show()
        dialog.setting()

    }
    private fun Dialog.setting() {
        this.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            attributes.windowAnimations = R.style.DialogAnimation
            setGravity(Gravity.BOTTOM)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

enum class DialogType{
    LIHAT, UPDATE
}
