package com.dergoogler.webui.activity

import android.app.Activity
import android.content.Context
import android.webkit.JavascriptInterface
import android.webkit.WebView

const val interfaceName = "activity"

fun instance(context: Context, webView: WebView): Any {
    val activity = context as Activity

    return object {
        @JavascriptInterface
        fun finishAffinity() {
            activity.finishAffinity()
        }
    }
}