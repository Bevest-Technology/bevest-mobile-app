package com.bevesttech.bevest.ui.businessowner.ownerregistration

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class OwnerRegistrationViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = OwnerRegistrationActivity.MAX_PAGE

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = HaveBusinessEntityFragment()
            1 -> fragment = HaveBusinessEntityFragment()
            2 -> fragment = HaveBusinessEntityFragment()
        }
        return fragment as Fragment
    }

}