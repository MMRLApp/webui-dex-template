package dev.mmrl.webui.module

import android.webkit.JavascriptInterface
import com.dergoogler.webui.plugin.Plugin
import dev.mmrl.webui.module.model.Properties

class Module(
    private val plugin: Plugin,
) {
    private val fs = plugin.fileManager

    private fun parsePropertiesString(input: String): Properties {
        val map = input.trim().lines().associate { line ->
            val (key, value) = line.split("=", limit = 2)
            key to value
        }

        return Properties(
            id = map["id"],
            name = map["name"],
            version = map["version"],
            versionCode = map["versionCode"]?.toIntOrNull(),
            author = map["author"],
            updateJson = map["updateJson"],
            description = map["description"]
        )
    }

    @get:JavascriptInterface
    val props
        get(): Properties {
            val properties = fs.readText("/data/adb/modules/${plugin.modId}/module.prop")
            return parsePropertiesString(properties)
        }
}