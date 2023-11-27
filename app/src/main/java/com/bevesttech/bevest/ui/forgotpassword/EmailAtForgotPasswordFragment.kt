package com.bevesttech.bevest.ui.forgotpassword

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bevesttech.bevest.R
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.databinding.FragmentEmailAtForgotPasswordBinding
import com.bevesttech.bevest.ui.login.LoginActivity
import com.bevesttech.bevest.utils.Utils
import com.bevesttech.bevest.utils.ViewModelFactory
import com.bevesttech.bevest.utils.disabled
import com.bevesttech.bevest.utils.enabled
import com.bevesttech.bevest.utils.gone
import com.bevesttech.bevest.utils.visible


class EmailAtForgotPasswordFragment : Fragment() {
    private var _binding: FragmentEmailAtForgotPasswordBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ForgotPasswordViewModel by viewModels {
        ViewModelFactory(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEmailAtForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListener()
    }

    private fun setListener() {
        with(binding) {
            btnSend.setOnClickListener {
                val email = edtEmail.text.toString()
                if (Utils.isValidEmail(email)) {
                    resetPassword(email)
                } else {
                    edtEmail.error = getString(R.string.email_tidak_valid)
                }
            }
        }
    }

    private fun resetPassword(email: String) {
        with(binding) {
            viewModel.forgotPassword(email).observe(requireActivity()) { result ->
                when (result) {
                    is Result.Loading -> {
                        progressIndicator.visible()
                        btnSend.disabled()
                    }

                    is Result.Success -> {
                        progressIndicator.gone()
                        btnSend.enabled()
                        Toast.makeText(requireActivity(), result.data, Toast.LENGTH_SHORT)
                            .show()
                        Intent(requireActivity(), LoginActivity::class.java).also { startActivity(it) }
                    }

                    is Result.Error -> {
                        progressIndicator.gone()
                        btnSend.enabled()
                        Toast.makeText(requireActivity(), result.error, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}