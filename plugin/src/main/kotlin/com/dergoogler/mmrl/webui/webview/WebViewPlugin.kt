package com.dergoogler.mmrl.webui.webview

import android.webkit.JavascriptInterface
import android.webkit.WebView

const val instanceName = "webview"

fun instance(webView: WebView): Any {
    return object {
        @JavascriptInterface
        fun loadUrl(url: String) {
            webView.loadUrl(url)
        }

        @JavascriptInterface
        fun setUserAgentString(agent: String) {
            webView.post {
                webView.settings.userAgentString = agent
            }
        }
    }
}