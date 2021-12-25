package com.github.nyafunta.togowlnative.common.main

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*


actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(OkHttp)

actual fun initLogger() {
    android.util.Log.e("nyafunta", "initLogger")
    Napier.base(DebugAntilog())
}