package com.github.nyafunta.togowlnative.infra.preference.di

import com.github.nyafunta.togowlnative.domain.infra.auth.TodoistAuthenticationRepository
import com.github.nyafunta.togowlnative.domain.infra.auth.TogglAuthenticationRepository
import com.github.nyafunta.togowlnative.infra.preference.auth.EncryptedAuthTokenDataStore
import com.github.nyafunta.togowlnative.infra.preference.auth.RawAuthTokenDataStore
import com.github.nyafunta.togowlnative.infra.preference.auth.RawAuthTokenDataStoreImpl
import com.github.nyafunta.togowlnative.infra.preference.auth.TodoistAuthTokenDataStore
import com.github.nyafunta.togowlnative.infra.preference.auth.TodoistAuthenticationRepositoryImpl
import com.github.nyafunta.togowlnative.infra.preference.auth.TogglAuthTokenDataStore
import com.github.nyafunta.togowlnative.infra.preference.auth.TogglAuthenticationRepositoryImpl
import com.github.nyafunta.togowlnative.model.ServiceType
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

actual val preferenceModule = module {

    single<TodoistAuthenticationRepository> {
        TodoistAuthenticationRepositoryImpl(get())
    }

    single<TogglAuthenticationRepository> {
        TogglAuthenticationRepositoryImpl(get())
    }

    single<RawAuthTokenDataStore>(qualifier = qualifier(ServiceType.Todoist)) {
        RawAuthTokenDataStoreImpl(androidApplication(), "todoist")
    }

    single<RawAuthTokenDataStore>(qualifier = qualifier(ServiceType.Toggl)) {
        RawAuthTokenDataStoreImpl(androidApplication(), "toggl")
    }

    single {
        TodoistAuthTokenDataStore(get(qualifier(ServiceType.Todoist)))
    }

    single {
        TogglAuthTokenDataStore(get(qualifier(ServiceType.Toggl)))
    }


    // 今は使ってないけど, 暗号化して保存したいお気持ちはあります.
    single(qualifier = qualifier(ServiceType.Todoist)) {
        EncryptedAuthTokenDataStore(androidApplication(), "todoist", "todoist")
    }

    single(qualifier = qualifier(ServiceType.Toggl)) {
        EncryptedAuthTokenDataStore(androidApplication(), "toggl", "toggl")
    }

}