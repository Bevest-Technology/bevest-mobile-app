package com.bevesttech.bevest.ui.investor.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.ActivityInvestorRegisterBinding

class InvestorRegisterActivity : AppCompatActivity() {

    private var _binding: ActivityInvestorRegisterBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInvestorRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        val fragmentProfile = FirstProfileResikoFragment()
        val fragment = fragmentManager.findFragmentByTag(FirstProfileResikoFragment::class.java.simpleName)

        if (fragment != fragmentProfile) {
            fragmentManager.commit {
                add(
                    R.id.fragment_container,
                    fragmentProfile,
                    FirstProfileResikoFragment::class.java.simpleName
                )
            }
        }

        binding.topAppbar.setNavigationOnClickListener {
            val secondFragment = fragmentManager.findFragmentByTag(SecondProfileResikoFragment::class.java.simpleName)
            print(secondFragment)
            if (secondFragment is SecondProfileResikoFragment) {
                fragmentManager.popBackStack()
            } else {
                finish()
            }
        }

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}