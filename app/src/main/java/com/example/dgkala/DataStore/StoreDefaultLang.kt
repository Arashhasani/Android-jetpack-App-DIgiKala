package com.example.dgkala.DataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences

class StoreDefaultLang(private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore("DefaultLang")
        val DEFAULT_LANG_KEY = stringPreferencesKey("default_lang")
    }

    val getLang: Flow<String?> = context.dataStore.data.map { preference ->
        preference[DEFAULT_LANG_KEY] ?: ""

    }


    suspend fun saveDefaultLang(name: String) {
        context.dataStore.edit { preference ->
            preference[DEFAULT_LANG_KEY] = name
        }
    }
}