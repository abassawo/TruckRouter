plugins {
    id("com.android.application").version("7.4.0").apply(false)
    id("com.android.library").version("7.4.0").apply(false)
    id("com.google.dagger.hilt.android") version "2.44" apply false
    kotlin("android").version("1.8.0").apply(false)
    kotlin("multiplatform").version("1.8.0").apply(false)


}

buildscript {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        gradlePluginPortal()
        google()
    }
    plugins {
        id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
    }
    dependencies {
        val kotlinVersion = "1.8.0"
        classpath(kotlin("serialization", version = kotlinVersion))
    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
