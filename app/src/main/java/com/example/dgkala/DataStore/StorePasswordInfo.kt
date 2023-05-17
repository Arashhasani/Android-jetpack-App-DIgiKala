package com.example.dgkala.DataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences

class StorePasswordInfo(private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore("StorePasswordInfo")
        val DEFAULT_PASSWORD_KEY = stringPreferencesKey("password_info")
    }

    val getPassword: Flow<String?> = context.dataStore.data.map { preference ->
        preference[DEFAULT_PASSWORD_KEY] ?: ""

    }


    suspend fun savePassword(name: String) {
        context.dataStore.edit { preference ->
            preference[DEFAULT_PASSWORD_KEY] = name
        }
    }
}