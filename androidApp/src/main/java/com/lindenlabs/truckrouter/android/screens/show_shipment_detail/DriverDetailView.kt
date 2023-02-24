package com.lindenlabs.truckrouter.android.screens.show_shipment_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.lindenlabs.truckrouter.presentation.ScheduleViewEntity

@Composable
fun DriverDetailView(
    entity: ScheduleViewEntity,
    navController: NavController,
) {

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxSize()
                .padding(padding)
        ) {
            TopAppBar(
                title = { Text(text = entity.driverName) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(Icons.Rounded.ArrowBack, "")
                    }
                })
            Text(text = entity.destinationAddress)
            Text(text = "Suitability Score ${entity.score}")
        }
    }
}