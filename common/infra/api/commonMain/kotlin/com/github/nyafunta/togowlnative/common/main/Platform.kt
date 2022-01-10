package com.github.nyafunta.togowlnative.common.main


import io.ktor.client.*

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient

expect fun initLogger()

expect fun base64(target: String): String