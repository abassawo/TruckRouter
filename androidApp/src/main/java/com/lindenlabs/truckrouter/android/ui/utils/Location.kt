package com.lindenlabs.truckrouter.android.ui.utils

import com.google.android.gms.maps.model.LatLng

object Location {
    private val locations =
        listOf(
            LatLng(40.73, -73.9732),
            LatLng(40.82 , -74.696612),
            LatLng(43.82 , -71.696612),
            LatLng(40.00 , -75.696612)
        )

    fun forAddress(address: String) = locations.random()
}