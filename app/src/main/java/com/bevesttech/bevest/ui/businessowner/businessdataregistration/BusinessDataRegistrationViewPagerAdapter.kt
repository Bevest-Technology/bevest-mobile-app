package com.bevesttech.bevest.ui.businessowner.businessdataregistration

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class BusinessDataRegistrationViewPagerAdapter(activity: AppCompatActivity) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = BusinessDataRegistrationActivity.MAX_PAGE

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = BusinessEntityDataFragment()
            1 -> fragment = BusinessGeneralDataFragment()
            2 -> fragment = BusinessSalesValuationFragment()
            3 -> fragment = BusinessDataOptionalFragment()
        }
        return fragment as Fragment
    }

}