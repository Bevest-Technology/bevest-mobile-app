package com.bevesttech.bevest.ui.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.OnboardingPageBinding

class OnboardingViewPagerAdapter : RecyclerView.Adapter<PagerVH2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH2 {
        return PagerVH2(OnboardingPageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = OnboardingFragment.MAX_STEP

    override fun onBindViewHolder(holder: PagerVH2, position: Int) {
        with(holder) {
            if (position == 0) {
                bindingDesign.gifPageImg.setImageResource(R.drawable.onboarding_1)
                bindingDesign.tvTitle.text = this.itemView.context.getString(R.string.onboarding_title_1)
                bindingDesign.tvSubtitle.text = this.itemView.context.getString(R.string.onboarding_subtitle_1)
            } else if (position == 1) {
                bindingDesign.gifPageImg.setImageResource(R.drawable.onboarding_2)
                bindingDesign.tvTitle.text = this.itemView.context.getString(R.string.onboarding_title_2)
                bindingDesign.tvSubtitle.text = this.itemView.context.getString(R.string.onboarding_subtitle_2)
            } else {
                bindingDesign.gifPageImg.setImageResource(R.drawable.onboarding_3)
                bindingDesign.tvTitle.text = this.itemView.context.getString(R.string.onboarding_title_3)
                bindingDesign.tvSubtitle.text = this.itemView.context.getString(R.string.onboarding_subtitle_3)
            }
        }
    }
}

class PagerVH2(val bindingDesign: OnboardingPageBinding) : RecyclerView.ViewHolder(bindingDesign.root)
