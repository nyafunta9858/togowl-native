plugins {
    id("multiplatform-setup")
    id("android-lib-setup")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":domain"))
                api(project(":model"))

                // Serialization
                with(Deps.JetBrains.Kotlin(project)) {
                    with (serialization) {
                        implementation(core)
                        implementation(json)
                    }
                    implementation(reflect)
                }
                with(Deps.JetBrains.Kotlin.Coroutines) {
                    implementation(core)
                }

                // Koin
                with(Deps.Koin) {
                    implementation(core)
                }

                // logging
                with(Deps.Napier) {
                    implementation(core)
                }

            }
        }
        val commonTest by getting {
            dependencies {
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Deps.AndroidX.DataStore.core)

                with(Deps.Koin) {
                    implementation(android)
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
