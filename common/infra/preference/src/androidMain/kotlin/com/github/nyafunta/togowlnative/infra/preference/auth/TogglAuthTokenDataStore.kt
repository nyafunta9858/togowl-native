package com.github.nyafunta.togowlnative.infra.preference.auth

import com.github.nyafunta.togowlnative.model.auth.TogglAuthToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// 暗号化保存に対応しても冗長であればRepositoryでロジックを持つことも検討する
internal actual class TogglAuthTokenDataStore(
    dataStore: RawAuthTokenDataStore,
) : RawAuthTokenDataStore by dataStore {

    actual suspend fun save(token: TogglAuthToken) {
        save(token.value)
    }

    actual fun load(): Flow<TogglAuthToken> = token.map {
        TogglAuthToken(it)
    }

}