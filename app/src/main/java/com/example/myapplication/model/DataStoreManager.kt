package com.example.myapplication.model

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

class DataStoreManager(private val context: Context) {

    companion object {
        val TOGGLE_STATE = booleanPreferencesKey("toggle_state")
        val LANGUAGE = stringPreferencesKey("language")
    }

    val toggleState: Flow<Boolean> = context.dataStore.data
        .map { prefs ->
            prefs[TOGGLE_STATE] ?: false
        }

    val language: Flow<String> = context.dataStore.data
        .map { prefs ->
            prefs[LANGUAGE] ?: "en"
        }

    suspend fun saveToggleState(isOn: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[TOGGLE_STATE] = isOn
        }
    }

    suspend fun saveLanguage(language: String) {
        context.dataStore.edit { prefs ->
            prefs[LANGUAGE] = language
        }
    }
}