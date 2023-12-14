package com.bevesttech.bevest.ui.businessowner.businessdataregistration

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.ActivityBusinessDataRegistrationBinding
import com.bevesttech.bevest.ui.businessowner.ownerregistration.OwnerRegistrationViewPagerAdapter
import com.bevesttech.bevest.utils.setupAppBar

class BusinessDataRegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBusinessDataRegistrationBinding
    private lateinit var sectionPagerAdapter: BusinessDataRegistrationViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusinessDataRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setView()
        setListener()
    }

    private fun setView() {
        with(binding) {
            setupAppBar(toolbar, getString(R.string.data_bisnis))

            setViewPager()
            setNextButtonState()
        }
    }

    private fun setNextButtonState() {
        with(binding) {
            if (viewPager.currentItem == 0) {
//                sharedViewModel.haveBusinessEntity.observe(this@OwnerRegistrationActivity) {
//                    btnNext.isEnabled = it != -1
//                }
            }
        }
    }

    private fun setListener() {
        with(binding) {
            toolbar.setNavigationOnClickListener { finish() }

            btnNext.setOnClickListener {
                if (viewPager.currentItem == MAX_PAGE - 1) {

                } else {
                    viewPager.currentItem += 1
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
            sectionPagerAdapter = BusinessDataRegistrationViewPagerAdapter(this@BusinessDataRegistrationActivity)
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
            this@BusinessDataRegistrationActivity,
            if (stepPosition <= pagePosition) R.drawable.stepbarindicator_active else R.drawable.stepbarindicator_default
        )
    }

    companion object {
        const val MAX_PAGE = 4
    }
}