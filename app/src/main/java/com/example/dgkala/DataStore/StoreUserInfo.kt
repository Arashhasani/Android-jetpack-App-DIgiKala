package com.example.dgkala.DataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences

class StoreUserInfo(private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore("UserInfo")
        val DEFAULT_USER_KEY = stringPreferencesKey("user_info")
    }

    val getUserInfo: Flow<String?> = context.dataStore.data.map { preference ->
        preference[DEFAULT_USER_KEY] ?: ""

    }


    suspend fun saveUserInfor(name: String) {
        context.dataStore.edit { preference ->
            preference[DEFAULT_USER_KEY] = name
        }
    }
}