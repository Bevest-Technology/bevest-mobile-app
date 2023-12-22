package com.bevesttech.bevest.ui.investor.investorpersonaldata

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.bevesttech.bevest.MainActivity
import com.bevesttech.bevest.R
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.databinding.ActivityInvestorPersonalDataBinding
import com.bevesttech.bevest.ui.businessowner.businessdataregistration.BusinessDataRegistrationActivity
import com.bevesttech.bevest.utils.ViewModelFactory
import com.bevesttech.bevest.utils.blockInput
import com.bevesttech.bevest.utils.disabled
import com.bevesttech.bevest.utils.enabled
import com.bevesttech.bevest.utils.gone
import com.bevesttech.bevest.utils.setupAppBar
import com.bevesttech.bevest.utils.unblockInput
import com.bevesttech.bevest.utils.visible

class InvestorPersonalDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInvestorPersonalDataBinding
    private lateinit var sectionPagerAdapter: InvestorPersonalViewPagerAdapter
    private val sharedViewModel: InvestorPersonalDataViewModel by viewModels { ViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInvestorPersonalDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setView()
        setListener()
    }

    private fun setListener() {
        with(binding) {
            if (viewPager.currentItem == 2) {
                if (sharedViewModel.hasUploadKtp.value == true) {
                    viewPager.currentItem = 3
                } else {
                    Toast.makeText(
                        this@InvestorPersonalDataActivity,
                        getString(R.string.ambil_foto_ktp_terlebih_dahulu), Toast.LENGTH_SHORT
                    ).show()
                }
            } else if (viewPager.currentItem == 3) {
                sharedViewModel.validateKtpDetail()

                sharedViewModel.detailKtpFormState.observe(this@InvestorPersonalDataActivity) { state ->
                    if (state.isDataValid) {
                        sharedViewModel.setInvestorCoreData()
                            .observe(this@InvestorPersonalDataActivity) { result ->
                                when (result) {
                                    is Result.Loading -> {
                                        progressIndicator.visible()
                                        blockInput()
                                        btnNext.disabled()
                                    }

                                    is Result.Success -> {
                                        progressIndicator.gone()
                                        unblockInput()

                                        Intent(
                                            this@InvestorPersonalDataActivity,
                                            MainActivity::class.java
                                        ).also {
                                            startActivity(it)
                                        }
                                    }

                                    is Result.Error -> {
                                        progressIndicator.gone()
                                        unblockInput()
                                        btnNext.enabled()
                                        Toast.makeText(
                                            this@InvestorPersonalDataActivity,
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
    }

    private fun setViewPager() {
        with(binding) {
            sectionPagerAdapter =
                InvestorPersonalViewPagerAdapter(this@InvestorPersonalDataActivity)
            viewPager.adapter = sectionPagerAdapter
            viewPager.isUserInputEnabled = false

            viewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    step1.stepItem.background = stepItemState(0, position)
                    step2.stepItem.background = stepItemState(1, position)
                    step3.stepItem.background = stepItemState(2, position)
                }
            })
        }
    }

    private fun setView() {
        with(binding) {
            setupAppBar(toolbar, getString(R.string.data_pribadi_investor))

            setViewPager()
        }
    }

    private fun stepItemState(stepPosition: Int, pagePosition: Int): Drawable? {
        return ContextCompat.getDrawable(
            this@InvestorPersonalDataActivity,
            if (stepPosition <= pagePosition) R.drawable.stepbarindicator_active else R.drawable.stepbarindicator_default
        )
    }

    companion object {
        const val MAX_PAGE = 3
    }
}