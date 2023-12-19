package com.bevesttech.bevest.data.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.data.model.LoggedInUser
import com.bevesttech.bevest.data.source.remote.UserRemoteDataSource
import com.bevesttech.bevest.utils.Role
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl constructor(
    private val firebaseAuth: FirebaseAuth,
    private val userRemoteDataSource: UserRemoteDataSource,
) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override fun login(email: String, password: String): LiveData<Result<LoggedInUser>> = liveData {
        try {
            emit(Result.Loading)
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            result.user!!.let {
                val user = userRemoteDataSource.getUserByUID(it.uid)
                emit(Result.Success(user!!))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun loginWithGoogle(idToken: String): LiveData<Result<LoggedInUser>> = liveData {
        try {
            emit(Result.Loading)
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            val authResult = firebaseAuth.signInWithCredential(credential).await()
            authResult.user!!.let { currentUser ->
                if (authResult.additionalUserInfo?.isNewUser == true) {
                    currentUser.also {
                        val user = LoggedInUser(
                            uid = it.uid,
                            displayName = it.displayName,
                            photoUrl = it.photoUrl?.toString(),
                            email = it.email,
                            role = null,
                        )
                        userRemoteDataSource.addUser(user)
                        emit(Result.Success(user))
                    }
                } else {
                    val user = userRemoteDataSource.getUserByUID(currentUser.uid)
                    emit(Result.Success(user!!))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun signup(
        name: String,
        email: String,
        password: String,
    ): LiveData<Result<LoggedInUser>> = liveData {
        try {
            emit(Result.Loading)
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result.user!!.let {
                val user = LoggedInUser(
                    uid = it.uid,
                    displayName = name,
                    photoUrl = it.photoUrl?.path,
                    email = it.email,
                    role = null,
                )
                userRemoteDataSource.addUser(user)
                emit(Result.Success(user))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun updateUserRole(role: Role) = flow {
        try {
            emit(Result.Loading)
            userRemoteDataSource.updateUserRole(role, currentUser!!.uid).also {
                emit(Result.Success(it))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

    override fun forgotPassword(email: String): LiveData<Result<String>> = liveData {
        try {
            emit(Result.Loading)
            firebaseAuth.sendPasswordResetEmail(email).await().also {
                emit(Result.Success("Please check your email!"))
            }
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: AuthRepositoryImpl? = null

        fun getInstance(
            firebaseAuth: FirebaseAuth,
            userRemoteDataSource: UserRemoteDataSource,
        ): AuthRepositoryImpl = instance ?: synchronized(this) {
            instance ?: AuthRepositoryImpl(firebaseAuth, userRemoteDataSource)
        }.also { instance = it }
    }
}