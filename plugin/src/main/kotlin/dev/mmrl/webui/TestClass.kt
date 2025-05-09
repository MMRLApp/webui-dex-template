package dev.mmrl.webui

import android.webkit.JavascriptInterface
import com.dergoogler.mmrl.webui.interfaces.FileInterface
import com.dergoogler.mmrl.webui.interfaces.WXInterface
import com.dergoogler.mmrl.webui.interfaces.WXOptions

class TestClass(wxOptions: WXOptions) : WXInterface(wxOptions) {
    override var name: String = "test"

    val fm = FileInterface(wxOptions)

    @JavascriptInterface
    fun read(): String? {
        fm.write("/sdcard/TestClass.txt", "Hello World!")
        val data = fm.read("/sdcard/TestClass.txt")
        return data
    }

    @JavascriptInterface
    fun log(msg: String) {
        console.error(msg)
    }

    @JavascriptInterface
    fun add(a: Int, b: Int): Int {
        return a + b
    }
}