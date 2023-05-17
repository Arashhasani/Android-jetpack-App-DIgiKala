package com.example.dgkala.DataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences

class StoreLoginState(private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore("LoginState")
        val DEFAULT_LOGIN_STATE_KEY = stringPreferencesKey("login_state")
    }

    val getLoginState: Flow<String?> = context.dataStore.data.map { preference ->
        preference[DEFAULT_LOGIN_STATE_KEY] ?: ""

    }


    suspend fun saveLoginState(name: String) {
        context.dataStore.edit { preference ->
            preference[DEFAULT_LOGIN_STATE_KEY] = name
        }
    }
}