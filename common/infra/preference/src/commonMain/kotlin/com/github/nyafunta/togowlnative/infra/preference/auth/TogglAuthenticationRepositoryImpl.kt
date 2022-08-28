package com.github.nyafunta.togowlnative.infra.preference.auth

import com.github.nyafunta.togowlnative.domain.infra.auth.TogglAuthenticationRepository
import com.github.nyafunta.togowlnative.model.auth.TogglAuthToken
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

internal class TogglAuthenticationRepositoryImpl(
    private val dataStore: TogglAuthTokenDataStore,
) : TogglAuthenticationRepository {

    override suspend fun save(token: TogglAuthToken) {
        dataStore.save(token)
    }

    override fun load(): Flow<TogglAuthToken> = dataStore.load()

    override fun exists(): Flow<Boolean> = flow {
        load().count() > 0
    }

    override suspend fun clear() {
        dataStore.clear()
    }
}