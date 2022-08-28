package com.github.nyafunta.togowlnative.infra.api

import com.github.nyafunta.togowlnative.infra.api.model.todoist.Project
import com.github.nyafunta.togowlnative.infra.api.model.todoist.Task
import de.jensklingenberg.ktorfit.http.Field
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Multipart
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.PUT
import de.jensklingenberg.ktorfit.http.Part
import de.jensklingenberg.ktorfit.http.Query
import kotlinx.coroutines.flow.Flow

interface TodoistApi {

    @GET("projects")
    fun projects(): Flow<List<Project>>

    @GET("tasks")
    fun tasks(): Flow<List<Task>>

    @GET("sync")
    fun sync(
        @Query(value = "sync_token")
        syncToken: String = "*",
        @Query(value = "resource_types")
        resourceTypes: String = "[\"all\", \"-projects\"]"
    ): Flow<String>
}