plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = Deps.Android.compileSdk

    defaultConfig {
        applicationId = "com.github.nyafunta.togowlnative"
        minSdk = Deps.Android.minSdk
        targetSdk = Deps.Android.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
        }

        release {
            isMinifyEnabled = true
            proguardFiles.addAll(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    file("proguard-rules.pro")
                )
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    sourceSets {
        whenObjectAdded {
            java.srcDir("src/*/kotlin")
        }
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    packagingOptions {
        resources.excludes += "/META-INF/*"
    }
    namespace = "com.github.nyafunta.togowlnative"
}

dependencies {
    // modules
    implementation(project(":model"))
    implementation(project(":infra:api"))
    implementation(project(":infra:preference"))

    // libs
    implementation(Deps.AndroidX.Core.coreKtx)
    implementation(Deps.AndroidX.Activity.activityCompose)
    implementation(Deps.AndroidX.AppCompat.appCompat)
    implementation(Deps.AndroidX.Browser.browser)
    implementation(Deps.AndroidX.Compose.ui)
    implementation(Deps.AndroidX.Compose.animation)
    implementation(Deps.AndroidX.Compose.material)
//    implementation(Deps.AndroidX.Compose.material3)
    implementation(Deps.AndroidX.Compose.navigation)
    implementation(Deps.AndroidX.Compose.Accompanist.systemUiController)
    implementation(Deps.AndroidX.Compose.runtime)
    implementation(Deps.AndroidX.Compose.liveData)
    implementation(Deps.AndroidX.Compose.uiToolingPreview)
    implementation(Deps.AndroidX.Lifecycle.runtimeKtx)
    implementation(Deps.Koin.android)
    testImplementation(Deps.AndroidX.Test.junit4)
    androidTestImplementation(Deps.AndroidX.Test.junit4Ext)
    androidTestImplementation(Deps.AndroidX.Test.espressoCore)
    androidTestImplementation(Deps.AndroidX.Compose.uiTestJunit4)
    debugImplementation(Deps.AndroidX.Compose.uiTooling)
    debugImplementation(Deps.AndroidX.Compose.uiTestManifest)

    debugImplementation("com.facebook.flipper:flipper:0.144.0")
    debugImplementation("com.facebook.soloader:soloader:0.10.3")
    releaseImplementation("com.facebook.flipper:flipper-noop:0.144.0")
}