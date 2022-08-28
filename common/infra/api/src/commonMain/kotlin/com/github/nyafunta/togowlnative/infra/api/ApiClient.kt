package com.github.nyafunta.togowlnative.infra.api

import io.ktor.client.*
import io.ktor.client.engine.*

internal object ApiClient {

    fun create(
        engine: HttpClientEngine,
        config: HttpClientConfig<*>.() -> Unit = {},
    ): HttpClient = HttpClient(engine) {
        ApiClientConfiguration(this)
        config(this)
    }
}