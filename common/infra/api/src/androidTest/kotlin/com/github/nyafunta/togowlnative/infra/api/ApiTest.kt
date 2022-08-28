package com.github.nyafunta.togowlnative.infra.api

import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ApiTest {

    @Before
    fun init() {
        val engine = MockEngine {
            respond(
                content = ByteReadChannel(
                    """
[
    {
        "id": 2995104339,
        "project_id": 2203306141,
        "section_id": 7025,
        "parent_id": 2995104589,
        "content": "Buy Milk",
        "description": "",
        "comment_count": 10,
        "assignee": 2671142,
        "assigner": 2671362,
        "order": 1,
        "priority": 1,
        "url": "https://todoist.com/showTask?id=2995104339"
    },
    {
        "id": 2995104339,
        "project_id": 2203306141,
        "section_id": 7025,
        "parent_id": 2995104589,
        "content": "Buy Milk",
        "description": "",
        "comment_count": 10,
        "assignee": 2671142,
        "assigner": 2671362,
        "order": 1,
        "priority": 1,
        "url": "https://todoist.com/showTask?id=2995104339"
    },
]
"""
                ),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "${ContentType.Application.Json}")
            )
        }

    }

    @Test
    fun hogehoge() = runTest {
    }
}