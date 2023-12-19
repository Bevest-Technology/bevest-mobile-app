package com.bevesttech.bevest.ui.valuasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.ActivityValuasiBinding

class ValuasiActivity : AppCompatActivity() {
    private var _binding: ActivityValuasiBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityValuasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        val phoneCallFragment = PhoneCallFragment()
        val fragment = fragmentManager.findFragmentByTag(PhoneCallFragment::class.java.simpleName)

        if (fragment !is PhoneCallFragment) {
            fragmentManager.commit {
                replace(
                    R.id.fragment_valuasi_container,
                    phoneCallFragment,
                    PhoneCallFragment::class.java.simpleName
                )
            }
        }

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}