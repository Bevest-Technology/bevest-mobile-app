package com.bevesttech.bevest.ui.businesshome

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.ActivityBusinessHomeBinding

class BusinessHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBusinessHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBusinessHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_business_home)
        navView.setupWithNavController(navController)
    }
}