package com.github.nyafunta.togowlnative.infra.api

import io.ktor.client.*

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient {
    return HttpClient(config)
}