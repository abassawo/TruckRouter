package com.lindenlabs.truckrouter.android.ui.screens.show_shipment_detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.LatLng
import com.lindenlabs.truckrouter.android.ui.ThemeColors
import com.lindenlabs.truckrouter.android.ui.screens.show_shipment_detail.map.MapInit
import com.lindenlabs.truckrouter.android.ui.screens.show_shipment_detail.map.MarkerView
import com.lindenlabs.truckrouter.android.ui.utils.Feature
import com.lindenlabs.truckrouter.android.ui.utils.FeatureFlag
import com.lindenlabs.truckrouter.android.ui.utils.Location
import com.lindenlabs.truckrouter.presentation.ScheduleViewEntity
import com.lindenlabs.truckrouter.android.R as androidR

@Composable
fun DriverDetailView(
    isLandscape: Boolean = false,
    entity: ScheduleViewEntity,
    navController: NavController? = null
) {
    val markerText = entity.markerText
    val context = LocalContext.current
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .padding(0.dp, 0.dp)
        ) {
            entity.toTopAppBar(isLandscape, navController)

            val featureFlag = FeatureFlag(LocalContext.current)
            val flags = featureFlag.state.value
            val mapsEnabled = flags[Feature.GoogleMap] ?: false

            if (mapsEnabled) {
                Column(modifier = Modifier.background(Color.Transparent)) {
                    Button(onClick = { featureFlag.disable(Feature.GoogleMap) }) {
                        Text(text = context.getString(androidR.string.opt_out_of_map))
                    }
                    MapInit(entity = entity)
                }
            } else {
                MarkerView(title = markerText)
                val modifier = Modifier
                    .background(Color.Transparent)
                    .padding(16.dp, 0.dp)
                Column(modifier = Modifier.background(Color.Transparent)) {
                    Button(
                        modifier = modifier,
                        onClick = { context.openNativeGoogleMapApp(Location.forAddress(entity.destinationAddress)) }) {
                        Text(text = context.getString(androidR.string.directions))
                    }
                    Button(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .padding(16.dp, 0.dp),
                        onClick = {
                            featureFlag.enable(Feature.GoogleMap)
                            context.showMapsNotConfiguredMessage()
                        }
                    )
                    {
                        Text(
                            modifier = Modifier
                                .background(Color.Transparent),
                            text = context.getString(androidR.string.navigate_there)
                        )
                    }
                }
            }
        }
    }
}

private fun Context.openNativeGoogleMapApp(latLng: LatLng) {
    val gmmIntentUri = Uri.parse("google.navigation:q=" + latLng.latitude + "," + latLng.longitude + "&mode=d")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    startActivity(mapIntent)
}

fun Context.showMapsNotConfiguredMessage() {
    val message = getString(androidR.string.map_init_error)
    Toast.makeText(this, message, Toast.LENGTH_SHORT)
        .show()
}

@Composable
private fun ScheduleViewEntity.toTopAppBar(isLandscape: Boolean, navController: NavController?) {
    when {
        isLandscape -> TopAppBar(
            backgroundColor = Color.Transparent,
            title = { Text(text = driverName) }
        )
        else -> TopAppBar(
            backgroundColor = Color.Transparent,
            title = { Text(text = driverName) },
            navigationIcon = {
                IconButton(onClick = {
                    navController?.navigateUp()
                }) {
                    Icon(Icons.Rounded.ArrowBack, "", tint = ThemeColors.VioletHex)
                }
            }
        )
    }
}