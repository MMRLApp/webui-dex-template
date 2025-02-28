package com.dergoogler.mmrl.webui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.webkit.JavascriptInterface
import android.webkit.WebView

const val instanceName = "activity"
const val onlyForModule = "bindhosts"

fun instance(context: Context, webView: WebView): Any {
    val activity = context as Activity

    return object {
        @JavascriptInterface
        fun finishAffinity() {
            activity.finishAffinity()
        }
    }
}