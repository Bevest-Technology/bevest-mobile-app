package com.bevesttech.bevest.ui.investor.profileresiko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.ActivityProfileResikoBinding
import com.bevesttech.bevest.ui.investor.register.ProfileResikoViewModel
import com.bevesttech.bevest.utils.ViewModelFactory

class ProfileResikoActivity : AppCompatActivity() {
    private var _binding: ActivityProfileResikoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileResikoViewModel by viewModels {
        ViewModelFactory(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfileResikoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragmentManager = supportFragmentManager
        val resultFragment = ProfileResikoResultFragment()
//        resultFragment.tipeInvestor = viewModel.tipeInvestor
        val fragment = fragmentManager.findFragmentByTag(ProfileResikoResultFragment::class.java.simpleName)

        if (fragment != resultFragment) {
            fragmentManager.commit {
                add(
                    R.id.fragment_container,
                    resultFragment,
                    ProfileResikoResultFragment::class.java.simpleName
                )
            }
        }

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}