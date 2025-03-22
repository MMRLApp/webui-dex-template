package dev.mmrl.webui.module

import com.dergoogler.webui.plugin.Instance
import com.dergoogler.webui.plugin.Plugin

fun instance(plugin: Plugin): Instance {
    return Instance(
        name = "module",
        instance = Module(plugin),
    )
}