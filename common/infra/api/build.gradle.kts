plugins {
    id("multiplatform-setup")
    id("android-lib-setup")
    id("com.google.devtools.ksp") version "1.6.20-1.0.5"
    id("kotlinx-serialization")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":domain"))
                api(project(":model"))

                with(Deps.JetBrains.Kotlin(project)) {
                    implementation(reflect)
                    // Serialization
                    with(serialization) {
                        implementation(core)
                        implementation(json)
                    }
                }

                // Ktor
                with(Deps.Ktor) {
//                    implementation(clientCore)
                    implementation(clientAuth)
                    implementation(clientLogging)
                    implementation(serializationJson)
                    implementation(negotiation)
                }

                with(Deps.Ktorfit) {
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
                // Ktor
                with(Deps.Ktor) {
                    implementation(clientMock)
                }
                with(Deps.JetBrains.Kotlin.Coroutines) {
                    implementation(test)
                }
            }
        }

        val androidMain by getting {
            dependencies {
                with(Deps.Ktor) {
                    implementation(clientOkhttp)
                }
                with(Deps.Koin) {
                    implementation(android)
                }
            }
        }
        val androidDebug by getting {
            dependencies {
                implementation("com.facebook.flipper:flipper-network-plugin:0.144.0")
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

dependencies {
    add("kspCommonMainMetadata", Deps.Ktorfit.ksp)
//    add("kspCommonMainMetadata", Deps.Ktorfit.ksp)
    add("kspAndroid", Deps.Ktorfit.ksp)
}