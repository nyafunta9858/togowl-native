// We store Kotlin and Compose versions in gradle.properties to
// be able to override them on CI.
// You probably won't need this, so you can get rid of `project` in this file.
import org.gradle.api.Project

object Versions {
    const val jetpack = "1.1.0-beta01"
    const val ktor = "1.6.7"
    const val koin = "3.1.4"
    const val napier = "2.3.0"
    const val kotlinxSerialization = "1.3.2"
}
// DependencyHandlerの拡張でKotlinのを追加したい
object Deps {
    object JetBrains {
        class Kotlin(project: Project) {
            private val version = project.properties["kotlin.version"] ?: "1.5.31"
            val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
            val testCommon = "org.jetbrains.kotlin:kotlin-test-common:$version"
            val testJunit = "org.jetbrains.kotlin:kotlin-test-junit:$version"
            val testJs = "org.jetbrains.kotlin:kotlin-test-js:$version"
            val testAnnotationsCommon =
                "org.jetbrains.kotlin:kotlin-test-annotations-common:$version"

            val serialization by lazy {
                Serialization(version)
            }

            class Serialization internal constructor(kotlinVersion: Any) {
                val gradlePlugin = "org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion"
                private val version = Versions.kotlinxSerialization
                val core = "org.jetbrains.kotlinx:kotlinx-serialization-core:$version"
                val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:$version"
            }
        }

        class Compose(private val project: Project) {
            private val VERSION = project.properties["compose.version"]
            val gradlePlugin = "org.jetbrains.compose:compose-gradle-plugin:$VERSION"
        }
    }

    object Android {
        const val compileSdk = 31
        const val targetSdk = 31
        const val minSdk = 24

        object Tools {
            object Build {
                const val gradlePlugin = "com.android.tools.build:gradle:7.0.4"
            }
        }
    }

    object AndroidX {

        object Core {
            const val coreKtx = "androidx.core:core-ktx:1.7.0"
        }

        object AppCompat {
            const val appCompat = "androidx.appcompat:appcompat:1.3.0"
        }

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.4.0"
        }

        object Compose {
            private const val version = Versions.jetpack
            const val ui = "androidx.compose.ui:ui:${Versions.jetpack}"
            const val material3 = "androidx.compose.material3:material3:1.0.0-alpha01"
            const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
            const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4:$version"
            const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
            const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:$version"
        }

        object Lifecycle {
            const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0"
        }

        object Test {
            const val junit4 = "junit:junit:4.13.2"
            const val junit4Ext = "androidx.test.ext:junit:1.1.3"
            const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0"
        }

    }

    object Ktor {
        private const val version = Versions.ktor
        const val clientCore = "io.ktor:ktor-client-core:$version"
        const val clientJson = "io.ktor:ktor-client-json:$version"
        const val clientLogging = "io.ktor:ktor-client-logging:$version"
        const val clientSerialization = "io.ktor:ktor-client-serialization:$version"
        const val clientOkhttp = "io.ktor:ktor-client-okhttp:$version"
        const val clientJava = "io.ktor:ktor-client-java:$version"
        const val clientJs = "io.ktor:ktor-client-js:$version"
        const val serialization = "io.ktor:ktor-serialization:$version"
        const val clientMock = "io.ktor:ktor-client-mock:$version"
    }

    object Koin {
        private const val version = Versions.koin
        const val core = "io.insert-koin:koin-core:$version"
        const val android = "io.insert-koin:koin-android:$version"
        // test
        const val test = "io.insert-koin:koin-test:$version"
        const val junit4 = "io.insert-koin:koin-test-junit4:$version"
    }

    object Napier {
        private const val version = Versions.napier
        const val core = "io.github.aakira:napier:$version"
    }

//    object ArkIvanov {
//        object MVIKotlin {
//            private const val VERSION = "3.0.0-alpha01"
//            const val rx = "com.arkivanov.mvikotlin:rx:$VERSION"
//            const val mvikotlin = "com.arkivanov.mvikotlin:mvikotlin:$VERSION"
//            const val mvikotlinMain = "com.arkivanov.mvikotlin:mvikotlin-main:$VERSION"
//            const val mvikotlinLogging = "com.arkivanov.mvikotlin:mvikotlin-logging:$VERSION"
//            const val mvikotlinTimeTravel = "com.arkivanov.mvikotlin:mvikotlin-timetravel:$VERSION"
//            const val mvikotlinExtensionsReaktive = "com.arkivanov.mvikotlin:mvikotlin-extensions-reaktive:$VERSION"
//        }
//
//        object Decompose {
//            private const val VERSION = "0.3.1"
//            const val decompose = "com.arkivanov.decompose:decompose:$VERSION"
//            const val extensionsCompose = "com.arkivanov.decompose:extensions-compose-jetbrains:$VERSION"
//        }
//    }

//    object Badoo {
//        object Reaktive {
//            private const val VERSION = "1.1.22"
//            const val reaktive = "com.badoo.reaktive:reaktive:$VERSION"
//            const val reaktiveTesting = "com.badoo.reaktive:reaktive-testing:$VERSION"
//            const val utils = "com.badoo.reaktive:utils:$VERSION"
//            const val coroutinesInterop = "com.badoo.reaktive:coroutines-interop:$VERSION"
//        }
//    }

//    object Squareup {
//        object SQLDelight {
//            private const val VERSION = "1.5.0"
//
//            const val gradlePlugin = "com.squareup.sqldelight:gradle-plugin:$VERSION"
//            const val androidDriver = "com.squareup.sqldelight:android-driver:$VERSION"
//            const val sqliteDriver = "com.squareup.sqldelight:sqlite-driver:$VERSION"
//            const val nativeDriver = "com.squareup.sqldelight:native-driver:$VERSION"
//            const val sqljsDriver = "com.squareup.sqldelight:sqljs-driver:$VERSION"
//        }
//    }
}