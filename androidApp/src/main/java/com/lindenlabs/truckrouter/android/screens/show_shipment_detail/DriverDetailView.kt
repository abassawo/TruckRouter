package com.lindenlabs.truckrouter.android.screens.show_shipment_detail

import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.navigation.NavController
import com.lindenlabs.truckrouter.android.screens.show_shipment_detail.map.MapInit
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
                .padding(padding)
        ) {
            when {
                isLandscape -> TopAppBar(
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
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = entity.formattedDate + "\n" + entity.destinationAddress + "\n" + "Suitability score: " + entity.score,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = ""
                )
            }
            MapInit()
            AndroidView(modifier = Modifier.padding(16.dp), factory = { context ->
                CoordinatorLayout(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                    )
                    val button = Button(context)
                    button.text = "Navigate there"
                    button.setOnClickListener {
                        Toast.makeText(context, "Feature coming soon", Toast.LENGTH_SHORT).show()
                    }
                    addView(button)
                }
            })
        }
    }
}