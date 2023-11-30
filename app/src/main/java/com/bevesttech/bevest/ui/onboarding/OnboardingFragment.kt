package com.bevesttech.bevest.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.FragmentOnboardingBinding
import com.bevesttech.bevest.ui.bisnislisting.BisnisListingActivity
import com.bevesttech.bevest.ui.login.LoginActivity

class OnboardingFragment : Fragment() {
    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            vpPage.adapter = OnboardingViewPagerAdapter()
            vpPage.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            vpPage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    indicator.ivIndicator1.setImageResource(if (position == 0) R.drawable.onboarding_selected_dot else R.drawable.onboarding_default_dot)
                    indicator.ivIndicator2.setImageResource(if (position == 1) R.drawable.onboarding_selected_dot else R.drawable.onboarding_default_dot)
                    indicator.ivIndicator3.setImageResource(if (position == 2) R.drawable.onboarding_selected_dot else R.drawable.onboarding_default_dot)
                }
            })

            btnContinue.setOnClickListener {
                if (vpPage.currentItem == MAX_STEP - 1) {
                    Intent(activity, LoginActivity::class.java).also {
                        startActivity(it)
                    }
                } else {
                    vpPage.currentItem = vpPage.currentItem + 1
                }
            }

            btnSkip.setOnClickListener {
                Intent(activity, BisnisListingActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val MAX_STEP = 3
    }
}