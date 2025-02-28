package com.dergoogler.mmrl.webui.dialog

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.webkit.JavascriptInterface
import android.webkit.WebView

const val instanceName = "dialog"

fun instance(context: Context, webView: WebView): Any {
    return object {
        val activity = context as Activity

        val dialogBuilder = AlertDialog.Builder(activity)

        @JavascriptInterface
        fun setTitle(title: String) {
            dialogBuilder.setTitle(title)
        }


        @JavascriptInterface
        fun setMessage(message: String) {
            dialogBuilder.setMessage(message)
        }

        @JavascriptInterface
        fun setPositiveButton(text: String, callbackName: String) {
            dialogBuilder.setPositiveButton(text) { _, _ ->
                webView.post {
                    webView.loadUrl("javascript:window.$instanceName.$callbackName()")
                }
            }
        }

        @JavascriptInterface
        fun setNegativeButton(text: String, callbackName: String) {
            dialogBuilder.setNegativeButton(text) { _, _ ->
                webView.post {
                    webView.loadUrl("javascript:window.$instanceName.$callbackName()")
                }
            }
        }

        @JavascriptInterface
        fun show() {
            activity.runOnUiThread {
                dialogBuilder.show()
            }
        }
    }
}