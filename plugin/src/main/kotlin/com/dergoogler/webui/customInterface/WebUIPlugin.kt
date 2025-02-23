package com.dergoogler.webui.customInterface

import android.content.Context
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast

const val interfaceName = "customInterface"

fun instance(context: Context, webView: WebView): Any {
    return object {
        @JavascriptInterface
        fun sayHello(): String {
            return "Hello from plugin!"
        }

        @JavascriptInterface
        fun loadUrl(url: String) {
            webView.post {
                webView.loadUrl(url)
            }
        }

        @JavascriptInterface
        fun showToast(message: String) {
            webView.post {
                Toast.makeText(context, message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}