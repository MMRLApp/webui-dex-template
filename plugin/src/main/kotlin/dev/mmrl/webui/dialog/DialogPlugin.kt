package dev.mmrl.webui.dialog

import com.dergoogler.webui.plugin.Instance
import com.dergoogler.webui.plugin.Plugin

fun instance(plugin: Plugin): Instance {
    return Instance(
        name = "dialog",
        instance = Dialog(plugin),
    )
}