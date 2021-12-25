package com.github.nyafunta.togowlnative.common.main

import io.ktor.client.*

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient {
    return HttpClient(config)
}