plugins {
    alias(libs.plugins.android.application) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
    dependencies {
        classpath(libs.gradle)
        classpath(kotlin("gradle-plugin", version = "1.9.25"))
    }
}