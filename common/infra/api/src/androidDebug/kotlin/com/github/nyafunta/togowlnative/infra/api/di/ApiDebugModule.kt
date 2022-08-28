package com.github.nyafunta.togowlnative.infra.api.di

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import org.koin.dsl.module

val apiDebugModule = module {

    single {
        NetworkFlipperPlugin()
    }

    single {
        listOf(
            FlipperOkhttpInterceptor(get())
        )
    }
}