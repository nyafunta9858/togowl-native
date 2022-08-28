package com.github.nyafunta.togowlnative

import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.core.FlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader

class FlipperInitializer private constructor() {

    companion object {
        operator fun invoke(context: Context, plugins: List<FlipperPlugin>) {
            val ctx = context.applicationContext
            SoLoader.init(ctx, false)

            if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(ctx)) {
                AndroidFlipperClient.getInstance(ctx).apply {
                    addPlugin(InspectorFlipperPlugin(ctx, DescriptorMapping.withDefaults()))
                    plugins.forEach(::addPlugin)
                    start()
                }
            }
        }
    }
}