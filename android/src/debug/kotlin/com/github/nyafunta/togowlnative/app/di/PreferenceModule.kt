package com.github.nyafunta.togowlnative.app.di

import com.github.nyafunta.togowlnative.infra.preference.di.preferenceModule
import org.koin.core.module.Module

class PreferenceModule private constructor() {

    companion object {
        operator fun invoke(): List<Module> = listOf(preferenceModule)
    }

}