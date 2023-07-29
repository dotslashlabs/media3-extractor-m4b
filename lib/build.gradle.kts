plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.dotslashlabs.media3.extractor.m4b"
    version = "1.0.0-SNAPSHOT"
    compileSdk = 33

    defaultConfig {
        minSdk = 16
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    val media3Version = "1.1.0"

    api("androidx.media3:media3-extractor:$media3Version")
    api("androidx.media3:media3-common:$media3Version")
    api("androidx.media3:media3-container:$media3Version")

    api("androidx.annotation:annotation:1.6.0")
}
