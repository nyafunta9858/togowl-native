plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    jvm("desktop")
    android()
    js(IR) {
        useCommonJs()
        browser()
    }
    sourceSets {
        named("commonTest") {
            dependencies {
                implementation(Deps.JetBrains.Kotlin(project).testCommon)
                implementation(Deps.JetBrains.Kotlin(project).testAnnotationsCommon)
            }
        }

        named("androidTest") {
            dependencies {
                implementation(Deps.JetBrains.Kotlin(project).testJunit)
            }
        }
        // TODO: remove todo when implements for desktop
        named("desktopTest") {
            dependencies {
//                implementation(Deps.JetBrains.Kotlin(project).testJunit)
            }
        }
        // TODO: remove todo when implements for web
        named("jsTest") {
            dependencies {
//                implementation(Deps.JetBrains.Kotlin(project).testJs)
            }
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}