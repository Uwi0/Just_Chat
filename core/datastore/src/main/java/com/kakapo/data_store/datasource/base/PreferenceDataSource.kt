package com.kakapo.data_store.datasource.base

interface PreferenceDataSource {
    suspend fun saveStringValue(key: String, value: String)

    suspend fun saveBooleanValue(key: String, value: Boolean)

    suspend fun saveIntValue(key: String, value: Int)

    suspend fun getStringValue(key: String): String

    suspend fun getBooleanValue(key: String): Boolean

    suspend fun getIntValue(key: String): Int
}