package com.bevesttech.bevest.ui.bisnislisting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.ActivityBisnisListingBinding

class BisnisListingActivity : AppCompatActivity() {
    private var _binding: ActivityBisnisListingBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBisnisListingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        val profileBisnisFragment = ProfileBisnisFragment()
        val keuanganFragment = KeuanganFragment()

        if (savedInstanceState == null) {
            fragmentManager.commit {
                add(
                    R.id.fragment_bisnislisting_container,
                    profileBisnisFragment,
                    ProfileBisnisFragment::class.java.simpleName)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}