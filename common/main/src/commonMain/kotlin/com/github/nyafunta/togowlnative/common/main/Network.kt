package com.github.nyafunta.togowlnative.common.main

import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

class Network {

    private val httpClient: HttpClient by lazy {
        initLogger()

        httpClient {
            install(JsonFeature) {
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

    suspend fun test(): List<Project> {
        return httpClient.get<String>(urlString = "https://api.todoist.com/rest/v1/projects") {
            header("Authorization", "Bearer $TOKEN")
        }.let {
            json.decodeFromString(it)
        }
    }

    private companion object {
        private const val TOKEN = ""

        private val json = Json {
            serializersModule = SerializersModule {
            }
            ignoreUnknownKeys = true
            coerceInputValues = true
            useAlternativeNames = false
        }
    }

}