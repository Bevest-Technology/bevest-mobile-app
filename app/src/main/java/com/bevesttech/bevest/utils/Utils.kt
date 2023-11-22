package com.bevesttech.bevest.utils

import android.util.Patterns

object Utils {
    fun isValidEmail(email: String): Boolean {
        return if (email.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else {
            email.isNotBlank()
        }
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }
}