package com.github.nyafunta.togowlnative.infra.preference.auth

import com.github.nyafunta.togowlnative.domain.infra.auth.TodoistAuthenticationRepository
import com.github.nyafunta.togowlnative.model.auth.TodoistAuthToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flow

internal class TodoistAuthenticationRepositoryImpl(
    private val dataStore: TodoistAuthTokenDataStore,
) : TodoistAuthenticationRepository {

    override suspend fun save(token: TodoistAuthToken) {
        dataStore.save(token)
    }

    override fun load(): Flow<TodoistAuthToken> = dataStore.load()

    override fun exists(): Flow<Boolean> = flow {
        load().count() > 0
    }

    override suspend fun clear() {
        dataStore.clear()
    }
}