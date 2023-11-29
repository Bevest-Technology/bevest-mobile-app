package com.bevesttech.bevest.ui.businessowner.ownerregistration

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.window.OnBackInvokedDispatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.ActivityOwnerRegistrationBinding
import com.bevesttech.bevest.utils.setupAppBar

class OwnerRegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOwnerRegistrationBinding
    private lateinit var sectionPagerAdapter: OwnerRegistrationViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOwnerRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setView()
        setListener()
    }

    private fun setView() {
        with(binding) {
            setupAppBar(toolbar, getString(R.string.data_pemilik_usaha))

            setViewPager()
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
        const val MAX_PAGE = 3
    }
}