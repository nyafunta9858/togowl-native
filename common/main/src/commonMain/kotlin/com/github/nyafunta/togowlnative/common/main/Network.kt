package com.github.nyafunta.togowlnative.common.main

import io.ktor.client.*
import io.ktor.client.request.*

class Network(private val httpClient: HttpClient) {

    private val TOGGL_TOKEN: String by lazy {
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