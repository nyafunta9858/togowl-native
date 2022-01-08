package com.github.nyafunta.togowlnative.common.main

import android.util.Base64
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import okhttp3.Call
import okhttp3.EventListener
import java.util.concurrent.TimeUnit


actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(OkHttp) {
    config(this)

    engine {
        config {
            retryOnConnectionFailure(true)
            connectTimeout(5, TimeUnit.SECONDS)
        }
    }
}

actual fun initLogger() {
    android.util.Log.e("nyafunta", "initLogger")
    Napier.base(DebugAntilog())
}

actual fun base64(target: String): String =
    Base64.encodeToString(target.encodeToByteArray(), Base64.NO_WRAP)