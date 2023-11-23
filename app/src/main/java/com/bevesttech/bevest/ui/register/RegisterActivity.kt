package com.bevesttech.bevest.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bevesttech.bevest.MainActivity
import com.bevesttech.bevest.R
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.databinding.ActivityRegisterBinding
import com.bevesttech.bevest.ui.chooserole.ChooseRoleActivity
import com.bevesttech.bevest.utils.Constants
import com.bevesttech.bevest.utils.ViewModelFactory
import com.bevesttech.bevest.utils.afterTextChanged
import com.bevesttech.bevest.utils.blockInput
import com.bevesttech.bevest.utils.disabled
import com.bevesttech.bevest.utils.enabled
import com.bevesttech.bevest.utils.gone
import com.bevesttech.bevest.utils.hideKeyboard
import com.bevesttech.bevest.utils.setupAppBar
import com.bevesttech.bevest.utils.unblockInput
import com.bevesttech.bevest.utils.visible
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension
import com.google.android.gms.common.api.ApiException

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var googleSignInClient: GoogleSignInClient
    private val viewModel: RegisterViewModel by viewModels { ViewModelFactory(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setGoogleSignIn()
        setView()
        setObserver()
        setListener()
        initResultLauncher()
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
        resultLauncher = registerForActivityResult(StartActivityForResult()) { result ->
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

    private fun loginWithGoogle(idToken: String) {
        with(binding) {
            viewModel.loginWithGoogle(idToken).observe(this@RegisterActivity) { response ->
                when (response) {
                    is Result.Loading -> {
                        progressIndicator.visible()
                        blockInput()
                        btnRegister.disabled()
                    }

                    is Result.Success -> {
                        progressIndicator.gone()
                        unblockInput()

                        if (viewModel.isUserHaveRole(response.data)) {
                            Intent(
                                this@RegisterActivity,
                                ChooseRoleActivity::class.java
                            ).also {
                                startActivity(it)
                            }
                        } else {
                            Intent(
                                this@RegisterActivity,
                                MainActivity::class.java
                            ).also {
                                startActivity(it)
                            }
                        }

                    }

                    is Result.Error -> {
                        progressIndicator.gone()
                        unblockInput()
                        btnRegister.enabled()
                        Toast.makeText(
                            this@RegisterActivity,
                            response.error.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun setObserver() {
        viewModel.registerFormState.observe(this) { registerState ->
            with(binding) {
                btnRegister.isEnabled = registerState.isDataValid

                if (registerState.nameError != null) {
                    edtName.error = getString(registerState.nameError)
                }

                if (registerState.emailError != null) {
                    edtEmail.error = getString(registerState.emailError)
                }

                if (registerState.passwordError != null) {
                    edtPassword.error = getString(registerState.passwordError)
                }

                if (registerState.confPasswordError != null) {
                    edtPasswordConfirmation.error = getString(registerState.confPasswordError)
                }
            }
        }
    }

    private fun setView() {
        with(binding) {
            btnRegister.disabled()

            setupAppBar(toolbar, getString(R.string.daftar))

            val afterTextChangedListener: (String) -> Unit = {
                viewModel.registerDataChanged(
                    name = edtName.text.toString(),
                    email = edtEmail.text.toString(),
                    password = edtPassword.text.toString(),
                    confPassword = edtPasswordConfirmation.text.toString()
                )
            }

            edtName.afterTextChanged(afterTextChangedListener)

            edtEmail.afterTextChanged(afterTextChangedListener)

            edtPassword.afterTextChanged(afterTextChangedListener)

            edtPasswordConfirmation.apply {
                afterTextChanged(afterTextChangedListener)

                setOnEditorActionListener { _, actionId, _ ->
                    when (actionId) {
                        EditorInfo.IME_ACTION_DONE -> {
                            signUpAction()
                        }
                    }
                    false
                }
            }
        }
    }

    private fun setListener() {
        with(binding) {
            toolbar.setNavigationOnClickListener { finish() }

            btnLogin.setOnClickListener { finish() }

            btnRegister.setOnClickListener {
                signUpAction()
            }

            btnRegisterWithGoogle.setOnClickListener {
                resultLauncher.launch(googleSignInClient.signInIntent)
            }
        }
    }

    private fun signUpAction() {
        with(binding) {
            hideKeyboard()

            val nameField = edtName.text.toString()
            val emailField = edtEmail.text.toString()
            val passwordField = edtPassword.text.toString()

            viewModel.signUp(nameField, emailField, passwordField)
                .observe(this@RegisterActivity) { result ->
                    when (result) {
                        is Result.Loading -> {
                            progressIndicator.visible()
                            blockInput()
                            btnRegister.disabled()
                        }

                        is Result.Success -> {
                            progressIndicator.gone()
                            unblockInput()
                            Intent(
                                this@RegisterActivity,
                                ChooseRoleActivity::class.java
                            ).also {
                                startActivity(it)
                            }
                        }

                        is Result.Error -> {
                            progressIndicator.gone()
                            unblockInput()
                            btnRegister.enabled()
                            Toast.makeText(
                                this@RegisterActivity,
                                result.error.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
        }
    }
}