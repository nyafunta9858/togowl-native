plugins {
    id("multiplatform-setup")
    id("android-lib-setup")
    kotlin("plugin.serialization")
}

kotlin {
    sourceSets {
        named("commonMain") {
            dependencies {
                // Ktor
                with(Deps.Ktor) {
                    implementation(clientCore)
                    implementation(clientJson)
                    implementation(clientLogging)
                    implementation(clientSerialization)
                }

                // Serialization
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.0")
            }
        }

        named("androidMain") {
            dependencies {
                implementation(Deps.Ktor.clientAndroid)
            }
        }

        named("jsMain") {
            dependencies {
                implementation(Deps.Ktor.clientJs)
            }
        }

        named("desktopMain") {
            dependencies {
                implementation(Deps.Ktor.clientJava)
            }
        }

        named("commonTest") {
            dependencies {
            }
        }
    }

}