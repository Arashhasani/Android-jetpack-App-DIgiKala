package com.example.dgkala.DataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences

class StoreDefaultDir(private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore("DefaultDir")
        val DEFAULT_DIR_KEY = stringPreferencesKey("default_dir")
    }

    val getDir: Flow<String?> = context.dataStore.data.map { preference ->
        preference[DEFAULT_DIR_KEY] ?: "Rtl"

    }


    suspend fun saveDefaultDir(name: String) {
        context.dataStore.edit { preference ->
            preference[DEFAULT_DIR_KEY] = name
        }
    }
}