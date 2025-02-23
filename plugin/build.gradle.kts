plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

val isDebuggableMMRL = true

dependencies {}

val androidHome: String = System.getenv("ANDROID_HOME")

val d8Bin: String = "$androidHome/build-tools/34.0.0/d8.bat"
val adbBin: String = "$androidHome/platform-tools/adb.exe"
val targetPackage = if (isDebuggableMMRL) "com.dergoogler.mmrl.debug" else "com.dergoogler.mmrl"
val appDir: String = "/data/data/$targetPackage/files"
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
        adbPush("$buildDir/classes.dex", "$appDir/plugins/webui-plugin.dex")
    }
}