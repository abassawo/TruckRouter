package com.lindenlabs.truckrouter.android.ui.screens.show_drivers

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
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
import com.lindenlabs.truckrouter.android.ui.ThemeColors
import com.lindenlabs.truckrouter.presentation.HomeViewEntity
import com.lindenlabs.truckrouter.presentation.ScheduleViewEntity

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowDriversScreen(
    viewEntity: HomeViewEntity,
    maxWidth: Float = viewEntity.maxCardWidth,
    clickAction: (schedule: ScheduleViewEntity) -> Unit,
) {
    Scaffold(modifier = Modifier.fillMaxWidth(maxWidth)) { padding ->
        Column(
            modifier =
            Modifier
                .padding(padding)
                .fillMaxWidth()
        ) {
            this@Column.AnimatedVisibility(
                visible = true,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                TopAppBar(
                    backgroundColor = Color.Transparent,
                    title = { Text(text = viewEntity.headerText) }
                )
            }
            Box(
                modifier = Modifier
                    .padding(PaddingValues(start = 16.dp))
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                this@Column.AnimatedVisibility(
                    visible = true,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Text(
                        modifier = Modifier.padding(0.dp, 8.dp),
                        text = "Total Suitability  " + viewEntity.totalSuitability,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(
                    items = viewEntity.schedules,
                    key = { it.driverName }
                ) { schedule ->
                    DriverItemView(
                        schedule = schedule,
                        backgroundColor = when {
                            viewEntity.highlightSelected -> if (schedule == viewEntity.getSelectedSchedule())  ThemeColors.VioletHex else Color.White
                            else -> Color.White
                        },
                        onDriverClick = {
                            clickAction(schedule)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .animateItemPlacement(),
                        isLandscape = maxWidth < 1f
                    )
                    Divider(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(2.dp)
                    )
                }
            }

        }
    }
}