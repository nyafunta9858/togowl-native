package com.github.nyafunta.togowlnative.infra.api

import com.github.nyafunta.togowlnative.infra.api.model.toggl.Project
import com.github.nyafunta.togowlnative.infra.api.model.toggl.Projects
import de.jensklingenberg.ktorfit.http.GET
import kotlinx.coroutines.flow.Flow

interface TogglApi {
    @GET("me")
    suspend fun userData(): String

    @GET("workspaces")
    fun workspaces(): Flow<List<Project>>
}
