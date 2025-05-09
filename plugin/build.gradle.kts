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
    compileOnly("dev.mmrl.MMRL:webui:500b3bae93")
}

val androidHome: String = System.getenv("ANDROID_HOME")

val d8Bin: String = "$androidHome/build-tools/34.0.0/d8.bat"
val adbBin: String = "$androidHome/platform-tools/adb.exe"
val buildDir: File = project.layout.buildDirectory.get().asFile

fun adbPush(vararg cmd: String) {
    exec {
        commandLine(android.adbExecutable.path, "push", *cmd)
    }
}

fun adbRootShell(vararg cmd: String) {
    exec {
        commandLine(android.adbExecutable.path, "shell", "su", "-c", "\"${cmd.joinToString(" ")}\"")
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
        d8("--output=$buildDir", classes.path)
        adbPush("$buildDir/classes.dex", "/data/adb/modules/bindhosts/webroot/plugins/webui.dex")
    }
}