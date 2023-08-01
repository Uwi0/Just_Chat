package com.kakapo.data_store.datasource.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.kakapo.data_store.datasource.base.PreferenceDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferenceDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): PreferenceDataSource {

    companion object {
        const val DEFAULT_STRING_VALUE = ""
        const val DEFAULT_BOOLEAN_VALUE = false
        const val DEFAULT_INT_VALUE = 0
    }

    override suspend fun saveStringValue(key: String, value: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    override suspend fun saveBooleanValue(key: String, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    override suspend fun saveIntValue(key: String, value: Int) {
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(key)] = value
        }
    }

    override suspend fun getStringValue(key: String): String {
        val preferences = dataStore.data.map { preference ->
            preference[stringPreferencesKey(key)] ?: DEFAULT_STRING_VALUE
        }
        return preferences.first()
    }

    override suspend fun getBooleanValue(key: String): Boolean {
        val preferences = dataStore.data.map { preference ->
            preference[booleanPreferencesKey(key)] ?: DEFAULT_BOOLEAN_VALUE
        }
        return preferences.first()
    }

    override suspend fun getIntValue(key: String): Int {
        val preferences = dataStore.data.map { preference ->
            preference[intPreferencesKey(key)] ?: DEFAULT_INT_VALUE
        }
        return preferences.first()
    }
}