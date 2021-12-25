plugins {
    id("multiplatform-setup")
    id("android-lib-setup")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                // Ktor
                with(Deps.Ktor) {
                    implementation(clientCore)
                    implementation(clientJson)
                    implementation(clientLogging)
                    implementation(clientSerialization)
                }

                // logging
                with(Deps.Napier) {
                    implementation(core)
                }

                // Serialization
                with(Deps.JetBrains.Kotlin(project).serialization) {
                    implementation(core)
                    implementation(json)
                }
            }
        }

        val androidMain by getting {
            dependencies {
                with(Deps.Ktor) {
                    implementation(clientOkhttp)
                }
            }
        }

//        // TODO: remove todo when implements for web
//        val jsMain by getting {
//            dependencies {
//                implementation(Deps.Ktor.clientJs)
//            }
//        }
//        // TODO: remove todo when implements for web
//        named("jsTest") {
//            dependencies {
//                implementation(Deps.JetBrains.Kotlin(project).testJs)
//            }
//        }
    }
}
