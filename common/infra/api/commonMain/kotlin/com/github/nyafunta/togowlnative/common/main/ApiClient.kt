package com.github.nyafunta.togowlnative.common.main

import io.ktor.client.*
import io.ktor.client.engine.*

class ApiClient {

    companion object Factory {
        fun create(
            engine: HttpClientEngine,
            config: HttpClientConfig<*>.() -> Unit
        ): HttpClient = HttpClient(engine, config)

        fun <T : HttpClientEngineConfig> create(
            engine: HttpClientEngineFactory<T>,
            config: HttpClientConfig<T>.() -> Unit
        ): HttpClient = HttpClient(engine, config)
    }
}