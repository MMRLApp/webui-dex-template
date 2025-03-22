package dev.mmrl.webui.dialog

import android.app.Activity
import android.app.AlertDialog
import android.webkit.JavascriptInterface
import com.dergoogler.webui.plugin.Plugin

class Dialog(plugin: Plugin) {
    private val activity = plugin.context as Activity
    private val webView = plugin.webView
    private val dialogBuilder = AlertDialog.Builder(activity)

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
                webView.loadUrl("javascript:window.dialog.$callbackName()")
            }
        }
    }

    @JavascriptInterface
    fun setNegativeButton(text: String, callbackName: String) {
        dialogBuilder.setNegativeButton(text) { _, _ ->
            webView.post {
                webView.loadUrl("javascript:window.dialog.$callbackName()")
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