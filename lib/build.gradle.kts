import com.vanniktech.maven.publish.SonatypeHost

val libVersion = "1.0.3"

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    id("com.vanniktech.maven.publish") version "0.25.3"
}

android {
    namespace = "com.dotslashlabs.media3.extractor.m4b"
    version = libVersion
    compileSdk = 33

    defaultConfig {
        minSdk = 24
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
    api("com.google.errorprone:error_prone_annotations:2.15.0")
}

mavenPublishing {
    coordinates("com.dotslashlabs", "media3-extractor-m4b", libVersion)

    pom {
        name.set("media3-extractor-m4b")
        description.set("Provides m4b file format extractor including support for extracting chapter metadata.")
        inceptionYear.set("2023")
        url.set("https://github.com/dotslashlabs/media3-extractor-m4b")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("shirish87")
                name.set("Shirish Kamath")
                url.set("https://github.com/shirish87/")
            }
        }
        scm {
            url.set("https://github.com/dotslashlabs/media3-extractor-m4b")
            connection.set("scm:git:git://github.com/dotslashlabs/media3-extractor-m4b.git")
            developerConnection.set("scm:git:ssh://git@github.com/dotslashlabs/media3-extractor-m4b.git")
        }
    }

    publishToMavenCentral(SonatypeHost.S01)
    signAllPublications()
}
