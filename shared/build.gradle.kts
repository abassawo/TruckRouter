plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.serialization")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
        sourceSets["androidMain"].resources.setSrcDirs(
            listOf("src/commonMain/res") // <-- add the commonMain Resources
        )
        sourceSets["commonMain"].dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
        }
    }
}

android {
    namespace = "com.lindenlabs.truckrouter"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}
