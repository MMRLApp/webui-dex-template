package dev.mmrl.webui.module.model

import android.webkit.JavascriptInterface

data class Properties(
    @get:JavascriptInterface
    val id: String?,
    @get:JavascriptInterface
    val name: String?,
    @get:JavascriptInterface
    val version: String?,
    @get:JavascriptInterface
    val versionCode: Int?,
    @get:JavascriptInterface
    val author: String?,
    @get:JavascriptInterface
    val updateJson: String?,
    @get:JavascriptInterface
    val description: String?,
)