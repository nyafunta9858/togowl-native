package com.github.nyafunta.togowlnative.common.main

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val id: Long,
    val name: String,
    @SerialName("comment_count")
    val commentCount: Int,
    val order: Int? = null,
    val color: Int,
    val shared: Boolean,
    @SerialName("sync_id")
    val syncId: Int,
    val favorite: Boolean,
    @SerialName("inbox_project")
    val inboxProject: Boolean = false,
    val url: String
)