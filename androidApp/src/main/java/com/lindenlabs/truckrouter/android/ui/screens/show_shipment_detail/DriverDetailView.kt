package com.lindenlabs.truckrouter.android.ui.screens.show_shipment_detail

import android.content.Context
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lindenlabs.truckrouter.android.ui.utils.Feature
import com.lindenlabs.truckrouter.android.ui.utils.FeatureFlag
import com.lindenlabs.truckrouter.android.ui.ThemeColors
import com.lindenlabs.truckrouter.android.ui.screens.show_shipment_detail.map.MarkerView
import com.lindenlabs.truckrouter.android.ui.screens.show_shipment_detail.map.MapInit
import com.lindenlabs.truckrouter.presentation.ScheduleViewEntity
import com.lindenlabs.truckrouter.android.R as androidR

@Composable
fun DriverDetailView(
    isLandscape: Boolean = false,
    entity: ScheduleViewEntity,
    navController: NavController? = null,
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
            with(FeatureFlag(LocalContext.current)) {
                when {
                    isAvailable(Feature.GoogleMap) -> MapInit(title = markerText)
                    else -> Column(modifier = Modifier.background(Color.Transparent)) {
                        MarkerView(title = markerText)
                        Button(
                            modifier = Modifier
                                .background(Color.Transparent)
                                .padding(16.dp, 0.dp),
                            onClick = { context.showMapsNotConfiguredMessage() }) {
                            Text(modifier = Modifier.background(Color.Transparent), text = "Navigate there")
                        }
                    }
                }
            }
        }
    }
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
                    Icon(Icons.Rounded.ArrowBack, "",  tint = ThemeColors.VioletHex)
                }
            }
        )
    }
}