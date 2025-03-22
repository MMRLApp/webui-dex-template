package dev.mmrl.webui.activity

import android.app.Activity
import android.webkit.JavascriptInterface
import com.dergoogler.webui.plugin.Plugin

class Actiwity(plugin: Plugin) {
    private val activity = plugin.context as Activity

    @JavascriptInterface
    fun finishAffinity() {
        activity.finishAffinity()
    }
}