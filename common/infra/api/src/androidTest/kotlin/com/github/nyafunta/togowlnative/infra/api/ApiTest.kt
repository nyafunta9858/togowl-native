package com.github.nyafunta.togowlnative.infra.api

import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.ktor.utils.io.*
import org.junit.Before

class ApiTest {

    @Before
    fun init() {
        val engine = MockEngine {
            respond(
                content = ByteReadChannel("""{"ip":"127.0.0.1"}"""),
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        ApiClient.create(engine) {

        }
    }

}