package com.bevesttech.bevest.ui.bevestacademy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bevesttech.bevest.R
import com.bevesttech.bevest.databinding.ActivityArticleBinding

class ArticleActivity : AppCompatActivity() {
    private var _binding: ActivityArticleBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}