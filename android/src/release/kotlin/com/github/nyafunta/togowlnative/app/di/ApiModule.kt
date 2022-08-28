package com.github.nyafunta.togowlnative.app.di

import com.github.nyafunta.togowlnative.infra.api.apiModule
import org.koin.core.module.Module

object ApiModule {

    operator fun invoke(): List<Module> = listOf(
        apiModule
    )

}