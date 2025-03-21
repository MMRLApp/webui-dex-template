package com.dergoogler.mmrl.webui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.webkit.JavascriptInterface
import android.webkit.WebView
import com.dergoogler.webui.plugin.Plugin

const val instanceName = "activity"
const val onlyForModule = "bindhosts"

data class Properties(
    @get:JavascriptInterface
    val id: String?,
    @get:JavascriptInterface
    val name: String?,
    @get:JavascriptInterface
    val version: String?,
    @get:JavascriptInterface
    val versionCode: Int?,
    @get:JavascriptInterface
    val author: String?,
    @get:JavascriptInterface
    val updateJson: String?,
    @get:JavascriptInterface
    val description: String?,
)

fun instance(plugin: Plugin): Any {
    val activity = plugin.context as Activity

    val fs = plugin.fileManager

    return object {
        @JavascriptInterface
        fun finishAffinity() {
            activity.finishAffinity()
        }

        @get:JavascriptInterface
        val modId
            get(): String {
                return plugin.modId
            }

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
}