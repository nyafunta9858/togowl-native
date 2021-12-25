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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets {
        whenObjectAdded {
            java.srcDir("src/*/kotlin")
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.jetpack
    }

    packagingOptions {
        resources.excludes += "/META-INF/*"
    }
}

dependencies {

    implementation(Deps.AndroidX.Core.coreKtx)
    implementation(Deps.AndroidX.Activity.activityCompose)
    implementation(Deps.AndroidX.AppCompat.appCompat)
    implementation(Deps.AndroidX.Compose.ui)
    // TODO: Composeを使うかJetpack Composeを使うかは使ってみて判断する
//    implementation(compose.material3)
    implementation(Deps.AndroidX.Compose.material3)
    implementation(Deps.AndroidX.Compose.uiToolingPreview)
    implementation(Deps.AndroidX.Lifecycle.runtimeKtx)
    testImplementation(Deps.AndroidX.Test.junit4)
    androidTestImplementation(Deps.AndroidX.Test.junit4Ext)
    androidTestImplementation(Deps.AndroidX.Test.espressoCore)
    androidTestImplementation(Deps.AndroidX.Compose.uiTestJunit4)
    debugImplementation(Deps.AndroidX.Compose.uiTooling)
    debugImplementation(Deps.AndroidX.Compose.uiTestManifest)

    implementation(project(":common:main"))
}