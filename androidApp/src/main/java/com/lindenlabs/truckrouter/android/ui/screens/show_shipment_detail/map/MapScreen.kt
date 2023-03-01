package com.lindenlabs.truckrouter.android.ui.screens.show_shipment_detail.map

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*
import com.lindenlabs.truckrouter.android.R
import com.lindenlabs.truckrouter.android.ui.utils.Location
import com.lindenlabs.truckrouter.presentation.ScheduleViewEntity

@Composable
fun MapInit(entity: ScheduleViewEntity) {
    val context = LocalContext.current
    val location = Location.forAddress(entity.destinationAddress)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 12f)
    }

    val marker = rememberMarkerState(position = location)
    val builder = LatLngBounds.Builder()
    builder.include(location)
    val mapProperties =
        MapProperties(
            maxZoomPreference = 16f,
            minZoomPreference = 5f,
            mapStyleOptions = MapStyleOptions.loadRawResourceStyle(context, R.raw.google_style)
        )


    val mapUiSettings = MapUiSettings(mapToolbarEnabled = false)


    GoogleMap(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = mapProperties,
        uiSettings = mapUiSettings
    ) {
        MarkerInfoWindowContent(
            state = marker,
            title = entity.markerText,
            onInfoWindowClick = {
                context.showFeatureNotAvailableYetMessage()
            }
        ) { marker ->
            MarkerView(title = marker.title ?: entity.markerText)
        }
    }
}

@Composable
fun MarkerView(title: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )
    }
}

private fun Context.showFeatureNotAvailableYetMessage() {
    val message = this.getString(R.string.feature_not_available)
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

