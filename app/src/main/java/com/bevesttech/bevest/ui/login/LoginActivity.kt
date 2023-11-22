package com.bevesttech.bevest.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bevesttech.bevest.databinding.ActivityLoginBinding
import com.bevesttech.bevest.utils.ViewModelFactory
import com.bevesttech.bevest.ui.chooserole.ChooseRoleActivity
import com.bevesttech.bevest.ui.forgotpassword.ForgotPasswordActivity
import com.bevesttech.bevest.ui.register.RegisterActivity
import com.bevesttech.bevest.utils.afterTextChanged
import com.bevesttech.bevest.utils.disabled

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels { ViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setView()
        setListener()
        setObserver()
    }

    private fun setView() {
        with(binding) {
            btnLogin.disabled()

            edtEmail.afterTextChanged {
                viewModel.loginDataChanged(
                    email = edtEmail.text.toString(),
                    password = edtPassword.text.toString()
                )
            }

            edtPassword.apply {
                afterTextChanged {
                    viewModel.loginDataChanged(
                        email = edtEmail.text.toString(),
                        password = edtPassword.text.toString()
                    )
                }

                setOnEditorActionListener { _, actionId, _ ->
                    when (actionId) {
                        EditorInfo.IME_ACTION_DONE -> {
                            // TODO: HANDLING LOGIN ACTION
                            true
                        }
                    }
                    false
                }
            }
        }
    }

    private fun setObserver() {
        viewModel.loginFormState.observe(this) { loginState ->
            with(binding) {
                btnLogin.isEnabled = loginState.isDataValid

                if (loginState.emailError != null) {
                    edtEmail.error = getString(loginState.emailError)
                }

                if (loginState.passwordError != null) {
                    edtPassword.error = getString(loginState.passwordError)
                }
            }
        }
    }

    private fun setListener() {
        with(binding) {
            btnLogin.setOnClickListener {
                Intent(this@LoginActivity, ChooseRoleActivity::class.java).also {
                    startActivity(it)
                }
            }

            btnRegister.setOnClickListener {
                Intent(this@LoginActivity, RegisterActivity::class.java).also {
                    startActivity(it)
                }
            }

            btnForgetPassword.setOnClickListener {
                Intent(this@LoginActivity, ForgotPasswordActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }
}