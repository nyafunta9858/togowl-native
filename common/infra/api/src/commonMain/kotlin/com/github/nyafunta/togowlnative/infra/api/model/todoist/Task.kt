package com.github.nyafunta.togowlnative.infra.api.model.todoist

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
{
    "assignee": 2671142,
    "assigner": 2671362,
    "comment_count": 10,
    "completed": true,
    "content": "Buy Milk",
    "description": "",
    "due": {
        "date": "2016-09-01",
        "datetime": "2016-09-01T09:00:00Z",
        "recurring": false,
        "string": "tomorrow at 12",
        "timezone": "Europe/Moscow"
    },
    "id": 2995104339,
    "label_ids": [
        2156154810,
        2156154820,
        2156154826
    ],
    "order": 1,
    "priority": 1,
    "project_id": 2203306141,
    "section_id": 7025,
    "parent_id": 2995104589,
    "url": "https://todoist.com/showTask?id=2995104339"
}
 */

@Serializable
data class Task(
    @SerialName("assignee")
    val assignee: Int? = null,
    @SerialName("assigner")
    val assigner: Int,
    @SerialName("comment_count")
    val commentCount: Int,
    val completed: Boolean,
    val content: String,
    val description: String,
    val due: Due? = null,
    val id: Long,
    @SerialName("label_ids")
    val labelIds: List<Long>,
    val order: Int,
    @SerialName("parent_id")
    val parentId: Long? = null,
    val priority: Int,
    @SerialName("project_id")
    val projectId: Long,
    @SerialName("section_id")
    val sectionId: Int,
    val url: String
)

@Serializable
data class Due(
    val date: String,
    @SerialName("datetime")
    val dateTime: String? = null,
    @SerialName("recurring")
    val recurring: Boolean,
    val string: String,
    @SerialName("timezone")
    val timeZone: String? = null
)

typealias Tasks = List<Task>