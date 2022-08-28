package com.github.nyafunta.togowlnative.domain.infra.auth

import com.github.nyafunta.togowlnative.model.auth.TodoistAuthToken
import kotlinx.coroutines.flow.Flow

interface TodoistAuthenticationRepository {

    suspend fun save(token: TodoistAuthToken)

    fun load(): Flow<TodoistAuthToken>

    fun exists(): Flow<Boolean>

    suspend fun clear()

}