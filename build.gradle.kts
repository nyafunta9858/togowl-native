// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    `kotlin-dsl`
}

allprojects {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
}

subprojects {
    if (hasProperty("kotlin") || hasProperty("android")) {
        apply(plugin = "detekt-setup")
    }
}

apply(plugin = "detekt-report-setup")
apply(plugin = "kover-setup")