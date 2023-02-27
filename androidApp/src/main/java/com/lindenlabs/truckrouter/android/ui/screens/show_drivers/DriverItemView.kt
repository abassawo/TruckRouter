package com.lindenlabs.truckrouter.android.ui.screens.show_drivers

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lindenlabs.truckrouter.presentation.ScheduleViewEntity

@Composable
fun DriverItemView(
    schedule: ScheduleViewEntity,
    backgroundColor: Color,
    onDriverClick: () -> Unit,
    modifier: Modifier = Modifier
) {
        Column(
            modifier = modifier
                .background(Color.LightGray)
                .clip(RoundedCornerShape(5.dp))
                .background(backgroundColor)
                .clickable { onDriverClick() }
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = schedule.driverName,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "",
                    modifier = Modifier
                        .clickable(MutableInteractionSource(), null) {
                        }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = schedule.destinationAddress,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            )
        }
}