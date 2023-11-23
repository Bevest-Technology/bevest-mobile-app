package com.bevesttech.bevest.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bevesttech.bevest.MainActivity
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.databinding.ActivityLoginBinding
import com.bevesttech.bevest.ui.chooserole.ChooseRoleActivity
import com.bevesttech.bevest.ui.forgotpassword.ForgotPasswordActivity
import com.bevesttech.bevest.ui.register.RegisterActivity
import com.bevesttech.bevest.utils.Constants
import com.bevesttech.bevest.utils.ViewModelFactory
import com.bevesttech.bevest.utils.afterTextChanged
import com.bevesttech.bevest.utils.blockInput
import com.bevesttech.bevest.utils.disabled
import com.bevesttech.bevest.utils.enabled
import com.bevesttech.bevest.utils.gone
import com.bevesttech.bevest.utils.hideKeyboard
import com.bevesttech.bevest.utils.unblockInput
import com.bevesttech.bevest.utils.visible
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var googleSignInClient: GoogleSignInClient
    private val viewModel: LoginViewModel by viewModels { ViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setGoogleSignIn()
        initResultLauncher()
        setView()
        setListener()
        setObserver()
    }

    private fun setGoogleSignIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(Constants.GOOGLE_SERVER_ID)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        googleSignInClient.signOut()
    }

    private fun initResultLauncher() {
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

                try {
                    val googleSignInAccount = task.getResult(ApiException::class.java)
                    googleSignInAccount?.apply {
                        idToken?.let { idToken ->
                            loginWithGoogle(idToken)
                        }
                    }
                } catch (e: Exception) {
                    print(e.message)
                }
            }
        }
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
                            loginAction()
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
                loginAction()
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
//                viewModel.logout()
            }

            btnLoginWithGoogle.setOnClickListener {
                resultLauncher.launch(googleSignInClient.signInIntent)
            }
        }
    }

    private fun loginWithGoogle(idToken: String) {
        with(binding) {
            viewModel.loginWithGoogle(idToken).observe(this@LoginActivity) { response ->
                when (response) {
                    is Result.Loading -> {
                        progressIndicator.visible()
                        blockInput()
                        btnLogin.disabled()
                    }

                    is Result.Success -> {
                        progressIndicator.gone()
                        unblockInput()

                        if (viewModel.isUserHaveRole(response.data)) {
                            Intent(
                                this@LoginActivity,
                                ChooseRoleActivity::class.java
                            ).also {
                                startActivity(it)
                            }
                        } else {
                            Intent(
                                this@LoginActivity,
                                MainActivity::class.java
                            ).also {
                                startActivity(it)
                            }
                        }

                    }

                    is Result.Error -> {
                        progressIndicator.gone()
                        unblockInput()
                        btnLogin.enabled()
                        Toast.makeText(
                            this@LoginActivity,
                            response.error.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun loginAction() {
        with(binding) {
            hideKeyboard()

            val emailField = edtEmail.text.toString()
            val passwordField = edtPassword.text.toString()

            viewModel.login(emailField, passwordField).observe(this@LoginActivity) { result ->
                when (result) {
                    is Result.Loading -> {
                        progressIndicator.visible()
                        blockInput()
                        btnLogin.disabled()
                    }

                    is Result.Success -> {
                        progressIndicator.gone()
                        unblockInput()
                        Intent(
                            this@LoginActivity,
                            ChooseRoleActivity::class.java
                        ).also {
                            startActivity(it)
                        }
                    }

                    is Result.Error -> {
                        progressIndicator.gone()
                        unblockInput()
                        btnLogin.enabled()
                        Toast.makeText(
                            this@LoginActivity,
                            result.error.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}