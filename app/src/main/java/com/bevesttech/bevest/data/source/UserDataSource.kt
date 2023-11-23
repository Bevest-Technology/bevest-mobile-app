package com.bevesttech.bevest.data.source

import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.data.model.LoggedInUser
import com.bevesttech.bevest.utils.Role

interface UserDataSource {

    suspend fun addUser(user: LoggedInUser)
    suspend fun getUserByUID(uid: String): LoggedInUser?
    suspend fun updateUserRole(role: Role, uid: String)
}