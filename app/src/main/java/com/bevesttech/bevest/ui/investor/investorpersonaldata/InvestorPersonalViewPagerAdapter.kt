package com.bevesttech.bevest.ui.investor.investorpersonaldata

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bevesttech.bevest.ui.businessowner.ownerregistration.HaveBusinessEntityFragment
import com.bevesttech.bevest.ui.businessowner.ownerregistration.OwnerKtpDetailFragment
import com.bevesttech.bevest.ui.businessowner.ownerregistration.OwnerKtpUploadFragment
import com.bevesttech.bevest.ui.businessowner.ownerregistration.OwnerRegistrationActivity
import com.bevesttech.bevest.ui.businessowner.ownerregistration.PersonalIdentityFragment

class InvestorPersonalViewPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = OwnerRegistrationActivity.MAX_PAGE

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = InvestorKtpUploadFragment()
            1 -> fragment = InvestorKtpDetailFragment()
            2 -> fragment = InvestorBankAccountFragment()
        }
        return fragment as Fragment
    }

}