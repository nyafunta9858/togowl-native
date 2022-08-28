package com.github.nyafunta.togowlnative.infra.preference.auth

import kotlinx.coroutines.flow.Flow

internal expect class EncryptedAuthTokenDataStore {
    suspend fun save(encrypted: EncryptedAuthToken)

    fun load(): Flow<EncryptedAuthToken>

    suspend fun clear()
}