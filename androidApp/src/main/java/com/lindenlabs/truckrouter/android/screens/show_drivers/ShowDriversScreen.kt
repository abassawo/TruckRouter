package com.lindenlabs.truckrouter.android.screens.show_drivers

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.lindenlabs.truckrouter.presentation.HomeViewEntity

const val RedOrangeHex = 0xffffab91
const val RedPinkHex = 0xfff48fb1
const val BabyBlueHex = 0xff81deea
const val VioletHex = 0xffcf94da
const val LightGreenHex = 0xffe7ed9b

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowDriversScreen(viewEntity: HomeViewEntity, navController: NavController) {
    val colors = listOf(RedOrangeHex, RedPinkHex, LightGreenHex, BabyBlueHex, VioletHex)

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Spacer(modifier = Modifier.height(4.dp))
            this@Column.AnimatedVisibility(
                visible = true,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                TopAppBar(
                    title = { Text(text = viewEntity.headerText) }
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                this@Column.AnimatedVisibility(
                    visible = true,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Text(
                        text = "Total Suitability  " + viewEntity.totalSuitability,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                }
            }
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(
                    items = viewEntity.schedules,
                    key = { it.driverName }
                ) { schedule ->
                    DriverItemView(
                        schedule = schedule,
                        backgroundColor = Color(colors.random()),
                        onDriverClick = {
                            navController.navigate("schedule_detail/${viewEntity.schedules.indexOf(schedule)}")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .animateItemPlacement()
                    )
                }
            }
        }
    }
}