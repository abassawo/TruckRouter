package com.lindenlabs.truckrouter.android.ui.screens.show_shipment_detail.map

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.*
import com.lindenlabs.truckrouter.android.R

@Composable
fun MapInit(title: String) {
    val context = LocalContext.current
    val newYork = LatLng(40.73, -73.9712)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(newYork, 12f)
    }
    val marker = rememberMarkerState(position = newYork)
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = mapProperties,
        uiSettings = mapUiSettings
    ) {
        MarkerInfoWindowContent(
            state = marker,
            title = title,
            onInfoWindowClick = {
                context.showFeatureNotAvailableYetMessage()
            }
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                BottomSheet(title = title)
            }
        }
    }
}

@Composable
fun BottomSheet(title: String){
    val context = LocalContext.current
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
        Icon(
            modifier = Modifier.padding(16.dp).clickable { context.showFeatureNotAvailableYetMessage() },
            imageVector = Icons.Default.LocationOn,
            contentDescription = "Navigate"
        )
    }
}

private fun Context.showFeatureNotAvailableYetMessage() {
    val message = this.getString(R.string.feature_not_available)
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

