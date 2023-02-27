package com.lindenlabs.truckrouter.android.ui.screens.show_shipment_detail

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lindenlabs.truckrouter.android.ui.screens.show_shipment_detail.map.MapInit
import com.lindenlabs.truckrouter.presentation.ScheduleViewEntity

@Composable
fun DriverDetailView(
    isLandscape: Boolean = false,
    entity: ScheduleViewEntity,
    navController: NavController? = null,
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .padding(0.dp, 0.dp)
        ) {
            isLandscape.toTopAppBar(entity, navController)
            MapInit(title = entity.destinationAddress + "\n" + "Suitability score: " + entity.score)
        }
    }
}
@Composable
private fun Boolean.toTopAppBar(entity: ScheduleViewEntity, navController: NavController?) {
    when {
        this -> TopAppBar(
            backgroundColor = Color.Transparent,
            title = { Text(text = entity.driverName) }
        )
        else -> TopAppBar(
            backgroundColor = Color.Transparent,
            title = { Text(text = entity.driverName) },
            navigationIcon = {
                IconButton(onClick = {
                    navController?.navigateUp()
                }) {
                    Icon(Icons.Rounded.ArrowBack, "")
                }
            }
        )
    }
}