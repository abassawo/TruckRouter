package com.lindenlabs.truckrouter.android.ui.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.compose.runtime.mutableStateOf
import javax.inject.Inject

enum class Feature {
    GoogleMap
}

class FeatureFlag @Inject constructor(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("featureFlag", MODE_PRIVATE)
    val state = mutableStateOf<Map<Feature, Boolean>>(emptyMap())

    init {
        updateFeatureFlags()
    }

    private fun updateFeatureFlags() {
        for (feature in Feature.values()) {
            state.value = state.value.toMutableMap().apply {
                val isAvailable = sharedPreferences.getBoolean(feature.name, false)
                put(feature, isAvailable)
            }
        }
    }

    fun enable(feature: Feature) = storeFeatureFlag(feature, true)

    fun disable(feature: Feature) = storeFeatureFlag(feature, false)

    private fun storeFeatureFlag(feature: Feature, value: Boolean) {
        sharedPreferences
            .edit()
            .putBoolean(feature.name, value)
            .apply()
            .also {
                updateFeatureFlags()
            }
    }
}