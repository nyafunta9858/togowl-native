plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
}

kotlin {
//    jvm("desktop")
    android()
//    js(IR) {
//        useCommonJs()
//        browser()
//    }
    sourceSets {
        val commonTest by getting {
            dependencies {
                implementation(Deps.JetBrains.Kotlin(project).testCommon)
                implementation(Deps.JetBrains.Kotlin(project).testAnnotationsCommon)
            }
        }

        val androidTest by getting {
            dependencies {
                implementation(Deps.JetBrains.Kotlin(project).testJunit)
            }
        }

//        // TODO: remove todo when implements for web
//        val jsMain by getting {
//            dependencies {
//                implementation(Deps.Ktor.clientJs)
//            }
//        }
//        val jsTest by getting {
//            dependencies {
//                implementation(Deps.JetBrains.Kotlin(project).testJs)
//            }
//        }

        // TODO: remove todo when implements for desktop
//        val desktopTest by getting {
//            dependencies {
//                implementation(Deps.JetBrains.Kotlin(project).testJunit)
//            }
//        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}