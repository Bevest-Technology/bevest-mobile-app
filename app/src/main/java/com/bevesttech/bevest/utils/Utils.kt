package com.bevesttech.bevest.utils

import android.util.Patterns
import com.google.common.base.Strings.isNullOrEmpty

enum class Role { BUSINESS, INVESTOR, NONE }

object Utils {
    fun isValidEmail(email: String): Boolean =
        email.contains('@') && email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isValidPassword(password: String): Boolean {
        return password.length >= 8
    }
}