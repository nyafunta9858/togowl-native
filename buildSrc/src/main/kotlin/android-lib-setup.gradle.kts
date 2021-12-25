plugins {
    id("com.android.library")
}

android {
    compileSdk = Deps.Android.compileSdk

    defaultConfig {
        minSdk = Deps.Android.minSdk
        targetSdk = Deps.Android.targetSdk
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}
