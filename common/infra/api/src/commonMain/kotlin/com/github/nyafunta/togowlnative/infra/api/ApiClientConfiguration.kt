package com.github.nyafunta.togowlnative.infra.api

import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.cache.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

internal object ApiClientConfiguration {

    operator fun <T : HttpClientEngineConfig> invoke(config: HttpClientConfig<T>) = with(config) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
                coerceInputValues = true
                useAlternativeNames = false
            })
        }

        install(HttpCache)

        install(HttpTimeout) {
            connectTimeoutMillis = 5000
            requestTimeoutMillis = 5000
            socketTimeoutMillis = 5000
        }

        HttpResponseValidator {
            validateResponse {
                Napier.i(it.toString())
            }
            handleResponseExceptionWithRequest { cause, request ->
                Napier.e("Request $request", cause)
            }
        }

        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.i(message)
                }
            }
        }
    }
}