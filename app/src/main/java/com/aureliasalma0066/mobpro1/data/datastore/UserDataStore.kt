package com.aureliasalma0066.mobpro1.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "user_pref"
)

class UserDataStore(
    private val context: Context
) {

    companion object {

        val LOGIN_KEY =
            booleanPreferencesKey("login")

        val NAME_KEY =
            stringPreferencesKey("name")

        val EMAIL_KEY =
            stringPreferencesKey("email")
    }

    val loginFlow =
        context.dataStore.data.map {
            it[LOGIN_KEY] ?: false
        }

    val nameFlow =
        context.dataStore.data.map {
            it[NAME_KEY] ?: ""
        }

    val emailFlow =
        context.dataStore.data.map {
            it[EMAIL_KEY] ?: ""
        }

    suspend fun saveUser(
        name: String,
        email: String
    ) {

        context.dataStore.edit {

            it[LOGIN_KEY] = true
            it[NAME_KEY] = name
            it[EMAIL_KEY] = email
        }
    }

    suspend fun logout() {

        context.dataStore.edit {

            it[LOGIN_KEY] = false
            it[NAME_KEY] = ""
            it[EMAIL_KEY] = ""
        }
    }
}