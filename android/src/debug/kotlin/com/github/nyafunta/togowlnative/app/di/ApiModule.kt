package com.github.nyafunta.togowlnative.app.di

import com.github.nyafunta.togowlnative.infra.api.di.apiDebugModule
import com.github.nyafunta.togowlnative.infra.api.di.apiModule
import org.koin.core.module.Module

class ApiModule private constructor(){

    companion object {
        operator fun invoke(): List<Module> = apiModule + apiDebugModule
    }

}