package com.github.nyafunta.togowlnative.domain.infra.auth

import com.github.nyafunta.togowlnative.model.auth.TogglAuthToken
import kotlinx.coroutines.flow.Flow

interface TogglAuthenticationRepository {

    suspend fun save(token: TogglAuthToken)

    fun load(): Flow<TogglAuthToken>

    fun exists(): Flow<Boolean>

    suspend fun clear()

}