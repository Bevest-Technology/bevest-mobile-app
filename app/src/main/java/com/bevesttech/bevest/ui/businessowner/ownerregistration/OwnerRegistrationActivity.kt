package com.bevesttech.bevest.ui.businessowner.ownerregistration

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.bevesttech.bevest.R
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.databinding.ActivityOwnerRegistrationBinding
import com.bevesttech.bevest.ui.businessowner.businessdataregistration.BusinessDataRegistrationActivity
import com.bevesttech.bevest.utils.ViewModelFactory
import com.bevesttech.bevest.utils.blockInput
import com.bevesttech.bevest.utils.disabled
import com.bevesttech.bevest.utils.enabled
import com.bevesttech.bevest.utils.gone
import com.bevesttech.bevest.utils.setupAppBar
import com.bevesttech.bevest.utils.unblockInput
import com.bevesttech.bevest.utils.visible

class OwnerRegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOwnerRegistrationBinding
    private lateinit var sectionPagerAdapter: OwnerRegistrationViewPagerAdapter
    private val sharedViewModel: OwnerRegistrationViewModel by viewModels { ViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOwnerRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setView()
        setListener()
        setObserver()
    }

    private fun setObserver() {

    }

    private fun setView() {
        with(binding) {
            setupAppBar(toolbar, getString(R.string.data_pemilik_usaha))

            setViewPager()
            setNextButtonState()
        }
    }

    private fun setNextButtonState() {
        with(binding) {
            if (viewPager.currentItem == MAX_PAGE - 1) {
                btnNext.text = getString(R.string.submit_data)
            }
        }
    }

    private fun setListener() {
        with(binding) {
            toolbar.setNavigationOnClickListener { finish() }

            btnNext.setOnClickListener {
                if (viewPager.currentItem == 0) {
                    viewPager.currentItem = 1
                } else if (viewPager.currentItem == 1) {
                    sharedViewModel.validateOwnerPersonalIdentity()

                    sharedViewModel.ownerPersonalIdentityFormState.observe(this@OwnerRegistrationActivity) { state ->
                        if (state.isDataValid) {
                            viewPager.currentItem = 2
                        }
                    }
                } else if (viewPager.currentItem == 2) {
                    if (sharedViewModel.hasUploadKtp.value == true) {
                        viewPager.currentItem = 3
                    } else {
                        Toast.makeText(
                            this@OwnerRegistrationActivity,
                            getString(R.string.ambil_foto_ktp_terlebih_dahulu), Toast.LENGTH_SHORT
                        ).show()
                    }
                } else if (viewPager.currentItem == 3) {
                    sharedViewModel.validateKtpDetail()

                    sharedViewModel.detailKtpFormState.observe(this@OwnerRegistrationActivity) { state ->
                        if (state.isDataValid) {
                            sharedViewModel.setOwnerRegistrationData().observe(this@OwnerRegistrationActivity) { result ->
                                when(result) {
                                    is Result.Loading -> {
                                        progressIndicator.visible()
                                        blockInput()
                                        btnNext.disabled()
                                    }

                                    is Result.Success -> {
                                        progressIndicator.gone()
                                        unblockInput()

                                        Intent(this@OwnerRegistrationActivity, BusinessDataRegistrationActivity::class.java).also {
                                            startActivity(it)
                                        }
                                    }

                                    is Result.Error -> {
                                        progressIndicator.gone()
                                        unblockInput()
                                        btnNext.enabled()
                                        Toast.makeText(
                                            this@OwnerRegistrationActivity,
                                            result.error.toString(),
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                }
            }

            step1.stepItem.setOnClickListener {
                if (viewPager.currentItem > 0) {
                    viewPager.currentItem = 0
                }
            }

            step2.stepItem.setOnClickListener {
                if (viewPager.currentItem > 1) {
                    viewPager.currentItem = 1
                }
            }

            step3.stepItem.setOnClickListener {
                if (viewPager.currentItem > 2) {
                    viewPager.currentItem = 2
                }
            }
        }
    }

    private fun setViewPager() {
        with(binding) {
            sectionPagerAdapter = OwnerRegistrationViewPagerAdapter(this@OwnerRegistrationActivity)
            viewPager.adapter = sectionPagerAdapter
            viewPager.isUserInputEnabled = false

            viewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    step1.stepItem.background = stepItemState(0, position)
                    step2.stepItem.background = stepItemState(1, position)
                    step3.stepItem.background = stepItemState(2, position)
                    step4.stepItem.background = stepItemState(3, position)
                }
            })
        }
    }

    private fun stepItemState(stepPosition: Int, pagePosition: Int): Drawable? {
        return ContextCompat.getDrawable(
            this@OwnerRegistrationActivity,
            if (stepPosition <= pagePosition) R.drawable.stepbarindicator_active else R.drawable.stepbarindicator_default
        )
    }

    companion object {
        const val MAX_PAGE = 4
    }
}