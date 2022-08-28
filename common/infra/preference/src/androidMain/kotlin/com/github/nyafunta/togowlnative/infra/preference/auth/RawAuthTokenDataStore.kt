package com.github.nyafunta.togowlnative.infra.preference.auth

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal interface RawAuthTokenDataStore {
    suspend fun save(token: String)
    val token: Flow<String>
    suspend fun clear()
}

internal class RawAuthTokenDataStoreImpl(
    private val context: Context,
    serviceName: String,
) : RawAuthTokenDataStore {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = "AuthToken_$serviceName"
    )

    private val key = stringPreferencesKey(serviceName)

    override suspend fun save(token: String) {
        context.dataStore.edit { preferences ->
            preferences[key] = token
        }
    }

    override val token: Flow<String>
        get() = context.dataStore
            .data
            .map { preferences ->
                preferences[key].orEmpty()
            }

    override suspend fun clear() {
        context.dataStore.edit { preferences -> preferences.clear() }
    }
}

