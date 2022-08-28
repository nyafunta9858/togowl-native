package com.github.nyafunta.togowlnative.infra.preference.auth

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal actual open class EncryptedAuthTokenDataStore(
    private val context: Context,
    fileName: String,
    preferenceKey: String,
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "AuthToken_$fileName")
    private val bytesToStringSeparator = "|"
    private val key = stringPreferencesKey(preferenceKey)

    actual suspend fun save(encrypted: EncryptedAuthToken) {
        context.dataStore.edit { preferences ->
            preferences[key] = encrypted.value.joinToString(separator = bytesToStringSeparator)
        }
    }

    actual fun load(): Flow<EncryptedAuthToken> {
        return context.dataStore.data.map { preferences ->
            EncryptedAuthToken(
                preferences[key].orEmpty()
                    .split(bytesToStringSeparator)
                    .map { it.toByte() }
                    .toByteArray()
            )
        }
    }

    actual suspend fun clear() {
        context.dataStore.edit { preferences -> preferences.clear() }
    }

}