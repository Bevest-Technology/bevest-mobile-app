package com.bevesttech.bevest.data.source.local

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.bevesttech.bevest.data.Result
import com.bevesttech.bevest.data.model.LoggedInUser
import com.bevesttech.bevest.utils.Onboarding
import com.bevesttech.bevest.utils.Role
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

class SessionPreference private constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun saveUserSession(user: LoggedInUser) {
        dataStore.edit { preferences ->
            preferences[USER_UID_KEY] = user.uid ?: ""
            preferences[USER_NAME_KEY] = user.displayName ?: ""
            preferences[USER_EMAIL_KEY] = user.email ?: ""
            preferences[USER_PHOTO_URL_KEY] = user.photoUrl ?: ""
            preferences[USER_ROLE_KEY] = user.role ?: ""
        }
    }

    suspend fun updateRole(role: Role) {
        dataStore.edit { preferences ->
            preferences[USER_ROLE_KEY] = role.name
        }
    }

    suspend fun saveOnboardingSession() {
        dataStore.edit { preferences ->
            preferences[ONBOARDING_KEY] = Onboarding.FINISH.toString()
        }
    }

    fun isAlreadyOnboarding(): Flow<Boolean>  {
        return dataStore.data.map { preferences ->
            preferences[ONBOARDING_KEY] == Onboarding.FINISH.toString()
        }
    }

    fun getUserSession(): Flow<LoggedInUser> {
        return dataStore.data.map { preferences ->
            LoggedInUser(
                uid = preferences[USER_NAME_KEY],
                displayName = preferences[USER_NAME_KEY],
                email = preferences[USER_EMAIL_KEY],
                photoUrl = preferences[USER_PHOTO_URL_KEY],
                role = preferences[USER_ROLE_KEY]
            )
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences[USER_UID_KEY] = ""
            preferences[USER_NAME_KEY] = ""
            preferences[USER_EMAIL_KEY] = ""
            preferences[USER_PHOTO_URL_KEY] = ""
            preferences[USER_ROLE_KEY] = ""
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SessionPreference? = null

        private val USER_UID_KEY = stringPreferencesKey("userUid")
        private val USER_NAME_KEY = stringPreferencesKey("userName")
        private val USER_PHOTO_URL_KEY = stringPreferencesKey("userPhotoUrl")
        private val USER_EMAIL_KEY = stringPreferencesKey("userEmail")
        private val USER_ROLE_KEY = stringPreferencesKey("userRole")
        private val ONBOARDING_KEY = stringPreferencesKey("onboarding")

        fun getInstance(dataStore: DataStore<Preferences>): SessionPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = SessionPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}