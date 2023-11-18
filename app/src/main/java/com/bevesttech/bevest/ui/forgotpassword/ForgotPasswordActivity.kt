package com.bevesttech.bevest.ui.forgotpassword

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.commit
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.ActivityForgotPasswordBinding
import com.bevesttech.bevest.ui.login.LoginActivity
import com.google.android.material.appbar.MaterialToolbar

class ForgotPasswordActivity : AppCompatActivity() {
    private var _binding: ActivityForgotPasswordBinding? = null
    private val binding get() = _binding!!

    private lateinit var topAppBar: MaterialToolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        topAppBar = binding.topAppbar
        topAppBar.setNavigationOnClickListener {
            Intent(this, LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }

        val fragmentManager = supportFragmentManager
        val emailAtForgotPasswordFragment = EmailAtForgotPasswordFragment()
        val fragment =
            fragmentManager.findFragmentByTag(EmailAtForgotPasswordFragment::class.java.simpleName)

        if (fragment !is EmailAtForgotPasswordFragment) {
            fragmentManager.commit {
                add(
                    R.id.frame_container,
                    emailAtForgotPasswordFragment,
                    EmailAtForgotPasswordFragment::class.java.simpleName
                )
            }
        }
    }
}