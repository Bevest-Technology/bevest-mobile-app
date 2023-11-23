package com.bevesttech.bevest.data.source.remote

import android.util.Log
import com.bevesttech.bevest.data.model.LoggedInUser
import com.bevesttech.bevest.data.source.UserDataSource
import com.bevesttech.bevest.utils.Constants.USERS_COL
import com.google.firebase.firestore.FirebaseFirestore

class UserRemoteDataSource : UserDataSource {
    private val db = FirebaseFirestore.getInstance()
    private val userCollections = db.collection(USERS_COL)

    override suspend fun addUser(user: LoggedInUser) {
        user.uid?.let {
            userCollections.document(user.uid).set(user)
                .addOnSuccessListener {
                    Log.d(TAG, "Success add user")
                }
                .addOnFailureListener {
                    Log.d(TAG, "Failed to add user")
                }
        }
    }

    companion object {
        private const val TAG = "UserRemoteDataSource"
    }
}