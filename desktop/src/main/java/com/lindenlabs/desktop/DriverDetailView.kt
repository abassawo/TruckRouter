package com.lindenlabs.desktop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lindenlabs.truckrouter.presentation.ScheduleViewEntity

@Composable
fun DriverDetailView(
    entity: ScheduleViewEntity
) {
    val markerText = entity.markerText
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
                .fillMaxSize()
                .padding(0.dp, 0.dp)
        ) {
            TopAppBar(
                backgroundColor = Color.Transparent,
                title = { Text(text = entity.driverName) }
            )

                MarkerView(title = markerText)
                val modifier = Modifier
                    .background(Color.Transparent)
                    .padding(16.dp, 0.dp)
                Column(modifier = Modifier.background(Color.Transparent)) {
                    Button(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .padding(16.dp, 0.dp),
                        onClick = {
                        }
                    )
                    {
                        Text(
                            modifier = Modifier
                                .background(Color.Transparent),
                            text = "Try cool in-app Map experience"
                        )
                    }
                }
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