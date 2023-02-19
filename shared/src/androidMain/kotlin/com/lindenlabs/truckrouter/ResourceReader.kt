package com.lindenlabs.truckrouter

import android.content.Context

// Android resources are available via the class loader
// References source code documented from https://luisramos.dev/how-to-share-resources-kmm

actual class ResourceReader(private val context: Context) {
    actual operator fun invoke(): String =
       context.resources.openRawResource(R.raw.shipping)
            .bufferedReader().use { it.readText() }
}