package com.github.nyafunta.togowlnative.infra.api

import io.ktor.client.engine.okhttp.*
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

actual val apiModule = module {
    single {
        ApiClient.create(OkHttp) {
            ApiClientConfig.create(this)

            engine {
                config {
                    retryOnConnectionFailure(true)
                    connectTimeout(5, TimeUnit.SECONDS)
                }
            }
        }
    }

    single { Network(get()) }
}
