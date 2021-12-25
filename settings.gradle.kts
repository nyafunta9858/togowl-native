pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "TogowlNative"

include(
    ":android",
//    ":web",
//    ":desktop",
)

include(
    ":common:main",
)
