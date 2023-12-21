package com.bevesttech.bevest.data.source.remote

import com.bevesttech.bevest.data.model.LoggedInUser
import com.bevesttech.bevest.data.source.UserDataSource
import com.bevesttech.bevest.utils.Constants
import com.bevesttech.bevest.utils.Constants.USERS_COL
import com.bevesttech.bevest.utils.Role
import com.bevesttech.bevest.utils.await
import com.google.firebase.firestore.FirebaseFirestore

class UserRemoteDataSource : UserDataSource {
    private val db = FirebaseFirestore.getInstance()
    private val userCollections = db.collection(USERS_COL)

    override suspend fun addUser(user: LoggedInUser) {
        user.uid?.let {
            userCollections.document(user.uid).set(user).await()
        }
    }

    override suspend fun getUserByUID(uid: String): LoggedInUser? {
        return userCollections.document(uid).get().await().toObject(LoggedInUser::class.java)
    }

    override suspend fun updateUserRole(role: Role, uid: String) {
        userCollections.document(uid).update(Constants.ROLE_FIELD, role.name).await()
    }

    companion object {
        private const val TAG = "UserRemoteDataSource"
    }
}