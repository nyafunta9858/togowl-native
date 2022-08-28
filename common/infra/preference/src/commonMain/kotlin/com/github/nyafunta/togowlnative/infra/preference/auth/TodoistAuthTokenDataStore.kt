package com.github.nyafunta.togowlnative.infra.preference.auth

import com.github.nyafunta.togowlnative.model.auth.TodoistAuthToken
import kotlinx.coroutines.flow.Flow

internal expect class TodoistAuthTokenDataStore {
    suspend fun save(token: TodoistAuthToken)

    fun load(): Flow<TodoistAuthToken>

    suspend fun clear()
}