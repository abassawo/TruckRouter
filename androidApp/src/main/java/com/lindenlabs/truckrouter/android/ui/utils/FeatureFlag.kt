package com.lindenlabs.truckrouter.android.ui.utils

import android.content.Context
import com.lindenlabs.truckrouter.android.R
import javax.inject.Inject

sealed class Feature {
    object GoogleMap : Feature()
}

class FeatureFlag @Inject constructor(private val context: Context) {

    fun isAvailable(feature: Feature): Boolean = when (feature) {
        Feature.GoogleMap -> context.getString(R.string.maps_api_key)
            .isEmpty()
            .not()
    }
}