package com.github.nyafunta.togowlnative.infra.api


import io.ktor.client.*

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient

expect fun initLogger()

expect fun base64(target: String): String