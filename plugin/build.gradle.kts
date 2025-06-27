plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 35
    namespace = "dev.mmrl.webui"

    defaultConfig {
        minSdk = 26
        multiDexEnabled = false
    }

    buildTypes {
        release {
            isShrinkResources = false
            multiDexEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    
    packaging.resources.excludes += setOf(
        "META-INF/**",
        "okhttp3/**",
        "kotlin/**",
        "org/**",
        "**.properties",
        "**.bin",
        "**/*.proto"
    )
}

dependencies {
    compileOnly(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    compileOnly("org.jetbrains.kotlinx:kotlinx-html-jvm:0.9.1")
    compileOnly("com.github.MMRLApp.MMRL:webui:4a41e7bdd7")
}

val isCI = System.getenv("CI") == "true"

val osName = System.getProperty("os.name").lowercase()
val d8Suffix = if (osName.contains("win")) ".bat" else ""
val adbSuffix = if (osName.contains("win")) ".exe" else ""

val androidHome: String = System.getenv("ANDROID_HOME")

val d8Bin: String = "$androidHome/build-tools/34.0.0/d8$d8Suffix"
val adbBin: String = "$androidHome/platform-tools/adb$adbSuffix"
val buildDir: File = project.layout.buildDirectory.get().asFile

fun adbPush(vararg cmd: String) {
    exec {
        commandLine(adbBin, "push", *cmd)
    }
}

fun d8(vararg cmd: String) {
    exec {
        commandLine(d8Bin, *cmd)
    }
}

val classes = buildDir.resolve("intermediates/aar_main_jar/release/syncReleaseLibJars/classes.jar")

tasks.register("build-dex") {
    dependsOn("build")

    doLast {
        d8("--output", "$buildDir", classes.path)
        if (!isCI) {
            adbPush("$buildDir/classes.dex", "/data/adb/modules/bindhosts/webroot/plugins/webui.dex")
        } else {
            println("Skipping adbPush in CI environment.")
        }
    }
}
