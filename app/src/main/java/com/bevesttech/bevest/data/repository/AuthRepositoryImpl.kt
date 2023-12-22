package com.bevesttech.bevest.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.data.model.LoggedInUser
import com.bevesttech.bevest.data.source.local.SessionPreference
import com.bevesttech.bevest.data.source.remote.UserRemoteDataSource
import com.bevesttech.bevest.utils.Role
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl constructor(
    private val firebaseAuth: FirebaseAuth,
    private val userRemoteDataSource: UserRemoteDataSource,
    private val sessionPreference: SessionPreference,
) : AuthRepository {

    init {
        runBlocking(Dispatchers.IO) {
            currentUser = sessionPreference.getUserSession().first()
        }
    }

    override var currentUser: LoggedInUser? = null

    override fun login(email: String, password: String): LiveData<Result<LoggedInUser>> = liveData {
        try {
            emit(Result.Loading)
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            result.user!!.let {
                val user = userRemoteDataSource.getUserByUID(it.uid)
                sessionPreference.saveUserSession(user!!)
                currentUser = user
                emit(Result.Success(user))
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
            authResult.user!!.let { userResult ->
                if (authResult.additionalUserInfo?.isNewUser == true) {
                    userResult.also {
                        val user = LoggedInUser(
                            uid = it.uid,
                            displayName = it.displayName,
                            photoUrl = it.photoUrl?.toString(),
                            email = it.email,
                            role = null,
                        )
                        sessionPreference.saveUserSession(user)
                        userRemoteDataSource.addUser(user)
                        emit(Result.Success(user))
                    }
                } else {
                    val user = userRemoteDataSource.getUserByUID(userResult.uid)
                    sessionPreference.saveUserSession(user!!)
                    currentUser = user
                    emit(Result.Success(user))
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
                sessionPreference.saveUserSession(user)
                userRemoteDataSource.addUser(user)
                currentUser = user
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
            userRemoteDataSource.updateUserRole(role, firebaseAuth.uid.toString()).also {
                emit(Result.Success(it))
            }
            sessionPreference.updateRole(role)
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    override suspend fun logout() {
        firebaseAuth.signOut()
        sessionPreference.logout()
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
            sessionPreference: SessionPreference,
        ): AuthRepositoryImpl = instance ?: synchronized(this) {
            instance ?: AuthRepositoryImpl(firebaseAuth, userRemoteDataSource, sessionPreference)
        }.also {
            instance = it
        }
    }
}