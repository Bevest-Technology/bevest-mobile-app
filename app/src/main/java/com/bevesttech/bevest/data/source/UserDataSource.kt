package com.bevesttech.bevest.data.source

import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.data.model.LoggedInUser

interface UserDataSource {

    suspend fun addUser(user: LoggedInUser)

    suspend fun getUserByUID(uid: String): LoggedInUser?

}