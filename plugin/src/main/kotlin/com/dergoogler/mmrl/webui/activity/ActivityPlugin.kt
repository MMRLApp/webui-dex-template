package com.dergoogler.mmrl.webui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.webkit.JavascriptInterface
import android.webkit.WebView
import com.dergoogler.webui.plugin.Plugin

const val instanceName = "activity"
const val onlyForModule = "bindhosts"

fun instance(plugin: Plugin): Any {
    val activity = plugin.context as Activity

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
    }
}