package com.bevesttech.bevest.data.model

data class LoggedInUser(
    val uid: String? = null,
    val displayName: String? = null,
    val photoUrl: String? = null,
    val email: String? = null,
    val role: String? = null,
)
