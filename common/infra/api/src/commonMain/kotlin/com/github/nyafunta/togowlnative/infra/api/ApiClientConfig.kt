package com.github.nyafunta.togowlnative.infra.api

import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

class ApiClientConfig {

    companion object Factory {
        fun <T : HttpClientEngineConfig> create(config: HttpClientConfig<T>) = with(config) {
            install(JsonFeature) {
                val json = kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                }
                serializer = KotlinxSerializer(json)
            }

            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.e(message)
                    }
                }
            }

            HttpResponseValidator {
                validateResponse {
                    Napier.e(it.toString())
                }
                handleResponseException {
                    Napier.e("", it)
                }
            }
        }
    }

}