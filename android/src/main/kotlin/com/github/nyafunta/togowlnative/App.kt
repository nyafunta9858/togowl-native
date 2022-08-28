package com.github.nyafunta.togowlnative

import android.app.Application
import com.facebook.flipper.core.FlipperPlugin
import com.github.nyafunta.togowlnative.app.di.ApiModule
import com.github.nyafunta.togowlnative.app.di.PreferenceModule
import org.koin.android.ext.android.get
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(ApiModule())
            modules(PreferenceModule())
        }

        val plugin = getKoin().getAll<FlipperPlugin>()
        FlipperInitializer(this, plugin)
    }
}