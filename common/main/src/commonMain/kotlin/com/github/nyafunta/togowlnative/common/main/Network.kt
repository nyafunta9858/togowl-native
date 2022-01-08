package com.github.nyafunta.togowlnative.common.main

import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

class Network {

    private val TOGGL_TOKEN: String by lazy {
    }

    private val httpClient: HttpClient by lazy {
        initLogger()

        httpClient {
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

    suspend fun projects(): Projects = httpClient.get(todoistRequestBuilder("projects"))

    private fun todoistRequestBuilder(append: String) = HttpRequestBuilder(
        scheme = "https",
        host = "api.todoist.com",
        path = "/rest/v1/$append"
    ).apply {
        header("Authorization", "Bearer $TOKEN")
    }

    suspend fun togglUseData() = httpClient.get<String>(togglRequestBuilder("me"))

    private fun togglRequestBuilder(append: String) = HttpRequestBuilder(
            scheme = "https",
            host = "api.track.toggl.com",
            path = "/api/v8/$append"
    ).apply {
        header("Authorization", "Basic $TOGGL_TOKEN")
    }

    suspend fun togglWorkspaces(): List<TogglProject> = httpClient.get(togglRequestBuilder("workspaces"))

    private companion object {
    }

}