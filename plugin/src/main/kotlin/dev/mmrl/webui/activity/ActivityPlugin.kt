package dev.mmrl.webui.activity

import com.dergoogler.webui.plugin.Instance
import com.dergoogler.webui.plugin.Plugin

fun instance(plugin: Plugin): Instance {
    return Instance(
        name = "activity",
        instance = Actiwity(plugin),
    )
}