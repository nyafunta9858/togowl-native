plugins {
    id("multiplatform-setup")
    id("android-lib-setup")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":model"))
            }
        }
        val commonTest by getting {
            dependencies {
            }
        }

        val androidMain by getting {
            dependencies {
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
