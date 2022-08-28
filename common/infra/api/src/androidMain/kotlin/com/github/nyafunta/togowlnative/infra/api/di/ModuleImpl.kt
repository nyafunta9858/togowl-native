package com.github.nyafunta.togowlnative.infra.api.di

import com.github.nyafunta.togowlnative.domain.infra.auth.TodoistAuthenticationRepository
import com.github.nyafunta.togowlnative.domain.infra.auth.TogglAuthenticationRepository
import com.github.nyafunta.togowlnative.infra.api.ApiClient
import com.github.nyafunta.togowlnative.model.ServiceType
import com.github.nyafunta.togowlnative.infra.api.TodoistApi
import com.github.nyafunta.togowlnative.infra.api.TogglApi
import com.github.nyafunta.togowlnative.infra.api.base64
import com.github.nyafunta.togowlnative.infra.api.model.todoist.Task
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.adapter.FlowResponseConverter
import de.jensklingenberg.ktorfit.adapter.ResponseConverter
import de.jensklingenberg.ktorfit.create
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import io.ktor.util.reflect.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.builtins.ListSerializer
import okhttp3.Interceptor
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

// expectにしてるけど, 他のプラットフォームで不要であればandroid mainのフォルダへ移動する
actual val apiModule = module {

    fun okHttpClient(
        interceptors: List<Interceptor> = emptyList(),
        networkInterceptors: List<Interceptor> = emptyList(),
        config: HttpClientConfig<*>.() -> Unit = {},
    ): HttpClient = ApiClient.create(OkHttp.create {
        config {
            retryOnConnectionFailure(true)
            interceptors.forEach { addInterceptor(it) }
            networkInterceptors.forEach { addNetworkInterceptor(it) }
        }
    }, config)

    single(qualifier = qualifier(ServiceType.Todoist)) {
        okHttpClient(networkInterceptors = get()) {
            install(Auth) {
                bearer {
                    loadTokens {
                        val token = get<TodoistAuthenticationRepository>()
                            .load()
                            .firstOrNull()
                            ?.value
                            .orEmpty()
                        BearerTokens(token, "")
                    }
                }
            }
        }
    }

    single(qualifier = qualifier(ServiceType.Todoist)) {
        Ktorfit("https://api.todoist.com/rest/v1/", get(qualifier(ServiceType.Todoist)))
            .addResponseConverter(FlowResponseConverter())
    }

    single {
        get<Ktorfit>(qualifier = qualifier(ServiceType.Todoist)).create<TodoistApi>()
    }

    single(qualifier = qualifier(ServiceType.Toggl)) {
        val interceptor = Interceptor { chain ->
            val request = chain.request()
            val storedToken = runBlocking {
                get<TogglAuthenticationRepository>()
                    .load()
                    .firstOrNull()
                    ?.value
                    .orEmpty()
            }
            val token = "Basic ${base64("$storedToken:api_token")}"
            val newRequest = request.newBuilder().header(HttpHeaders.Authorization, token).build()
            return@Interceptor chain.proceed(newRequest)
        }

        okHttpClient(interceptors = listOf(interceptor), networkInterceptors = get())
    }

    single(qualifier = qualifier(ServiceType.Toggl)) {
        Ktorfit("https://api.track.toggl.com/api/v8/", get(qualifier(ServiceType.Toggl)))
            .addResponseConverter(FlowResponseConverter())
    }

    single {
        get<Ktorfit>(qualifier = qualifier(ServiceType.Toggl)).create<TogglApi>()
    }

}
