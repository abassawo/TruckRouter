package com.lindenlabs.truckrouter.android

import android.content.Context
import javax.inject.Inject

sealed class Feature {
    object GoogleMap : Feature()
}

class FeatureFlag @Inject constructor(val context: Context) {

    fun isAvailable(feature: Feature): Boolean = when (feature) {
        Feature.GoogleMap -> context.getString(R.string.maps_api_key)
            .isEmpty()
            .not()
    }

}