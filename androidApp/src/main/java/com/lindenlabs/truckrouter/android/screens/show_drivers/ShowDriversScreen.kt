package com.lindenlabs.truckrouter.android.screens.show_drivers

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.navigation.NavController
import com.lindenlabs.truckrouter.android.WindowSize
import com.lindenlabs.truckrouter.android.WindowType
import com.lindenlabs.truckrouter.presentation.HomeViewEntity

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowDriversScreen(
    viewEntity: HomeViewEntity,
    navController: NavController,
    occupyMaxWidth: Boolean = true
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .background(Color.Black)
                .padding(padding)
                .wrapContentWidth()
//                .also {
//                    if(occupyMaxWidth) {
//                        it.fillMaxSize()
//                    } else {
//                        it.fillMaxWidth(WindowType.Compact.sizeThreshold.toFloat())
//                    }
//                }
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            this@Column.AnimatedVisibility(
                visible = true,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                TopAppBar(
                    backgroundColor = Color.Transparent,
                    title = { Text(color = Color.White, text = viewEntity.headerText) },

                    )
            }
            Box(
                modifier = Modifier
                    .padding(PaddingValues(start = 16.dp))
                    .wrapContentWidth(),
                contentAlignment = Alignment.Center
            ) {
                this@Column.AnimatedVisibility(
                    visible = true,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Text(
                        color = Color.White,
                        text = "Total Suitability  " + viewEntity.totalSuitability,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                }
            }
            LazyColumn(
                modifier = Modifier.wrapContentWidth()
            ) {
                items(
                    items = viewEntity.schedules,
                    key = { it.driverName }
                ) { schedule ->
                    DriverItemView(
                        schedule = schedule,
                        backgroundColor = Color.White,
                        onDriverClick = {
                            navController.navigate(
                                "schedule_detail/${
                                    viewEntity.schedules.indexOf(
                                        schedule
                                    )
                                }"
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .animateItemPlacement()
                    )
                    Divider(
                        color = Color.Blue, modifier = Modifier
                            .fillMaxHeight()
                            .width(2.dp)
                    )
                }
            }
        }
    }
}