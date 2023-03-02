plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.serialization")
}

kotlin {
    jvm {
//        compilations.all {
//            kotlinOptions.jvmTarget = "11"
//        }
//        withJava()
    }
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    sourceSets["commonMain"].dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
        implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    }
    sourceSets {
        val jvmMain by getting
        val androidMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
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
//dependencies {
//    testImplementation("org.junit.jupiter:junit-jupiter")
//}
