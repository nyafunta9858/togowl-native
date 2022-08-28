package com.github.nyafunta.togowlnative.navigation

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import androidx.browser.customtabs.CustomTabsIntent
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import java.net.URLDecoder

@Navigator.Name("chrome")
class ChromeCustomTabsNavigator(private val context: Context) :
    Navigator<ChromeCustomTabsNavigator.Destination>() {

    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Extras?,
    ): NavDestination? {
        val encodedUrl = args?.getString(ARGUMENT_NAME_URL)
            ?: throw IllegalStateException("Destination ${destination.id} does not have an url.")
        val url = URLDecoder.decode(encodedUrl, "utf-8")

        if (url.isInvalidWebUrl()) {
            throw IllegalArgumentException("Url($url) is a invalid web URL.")
        }

        val builder = CustomTabsIntent.Builder()
            .setShowTitle(true)
            .setUrlBarHidingEnabled(true)

        val intent = builder.build()
        try {
            intent.launchUrl(context, Uri.parse(url))
        } catch (e: ActivityNotFoundException) {
//            Logger.d(e, "Fail ChromeCustomTabsNavigator. launchUrl($url)")
        }
        return null // Do not add to the back stack, managed by Chrome Custom Tabs
    }

    private fun String.isInvalidWebUrl(): Boolean {
        return Patterns.WEB_URL.matcher(this).matches().not()
    }

    override fun createDestination() = Destination(this)

    override fun popBackStack() = true // Managed by Chrome Custom Tabs

    @NavDestination.ClassType(Activity::class)
    class Destination(navigator: Navigator<out NavDestination>) : NavDestination(navigator)

    companion object {
        const val ARGUMENT_NAME_URL: String = "url"
    }
}