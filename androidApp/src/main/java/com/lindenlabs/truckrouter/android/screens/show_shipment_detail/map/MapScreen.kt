package com.lindenlabs.truckrouter.android.screens.show_shipment_detail.map

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*
import com.lindenlabs.truckrouter.android.R

@Composable
fun MapInit() {
    val context = LocalContext.current
    val newYork = LatLng(40.73, -73.9712)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(newYork, 12f)
    }
    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                maxZoomPreference = 16f,
                minZoomPreference = 5f,
                mapStyleOptions = MapStyleOptions.loadRawResourceStyle(context, R.raw.google_style)
            )
        )
    }
    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(mapToolbarEnabled = false)
        )
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = mapProperties,
        uiSettings = mapUiSettings
    ) {
        Marker(position = LatLng(40.73, -73.9912), onClick = { marker ->
            Log.d("MapScreen", marker.id)
            true
        }
        )
    }
}