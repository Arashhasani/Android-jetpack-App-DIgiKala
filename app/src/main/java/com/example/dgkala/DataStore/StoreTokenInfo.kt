package com.example.dgkala.DataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences

class StoreTokenInfo(private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore("StoreTokenInfo")
        val DEFAULT_TOKEN_KEY = stringPreferencesKey("user_token")
    }

    val getToken: Flow<String?> = context.dataStore.data.map { preference ->
        preference[DEFAULT_TOKEN_KEY] ?: ""

    }


    suspend fun saveToken(name: String) {
        context.dataStore.edit { preference ->
            preference[DEFAULT_TOKEN_KEY] = name
        }
    }
}