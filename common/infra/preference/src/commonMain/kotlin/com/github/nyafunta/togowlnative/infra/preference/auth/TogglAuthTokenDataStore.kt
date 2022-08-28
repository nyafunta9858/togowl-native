package com.github.nyafunta.togowlnative.infra.preference.auth

import com.github.nyafunta.togowlnative.model.auth.TogglAuthToken
import kotlinx.coroutines.flow.Flow

internal expect class TogglAuthTokenDataStore {
    suspend fun save(token: TogglAuthToken)

    fun load(): Flow<TogglAuthToken>

    suspend fun clear()
}