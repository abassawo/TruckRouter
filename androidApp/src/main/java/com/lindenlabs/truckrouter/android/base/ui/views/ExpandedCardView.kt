package com.lindenlabs.truckrouter.android.base.ui.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lindenlabs.truckrouter.android.screens.show_drivers.ShowDriversScreen
import com.lindenlabs.truckrouter.android.screens.show_shipment_detail.DriverDetailView
import com.lindenlabs.truckrouter.presentation.HomeViewEntity
import com.lindenlabs.truckrouter.presentation.ScheduleViewEntity

/*
    Composite list and detail view that may best be use
 */
@Composable
fun ExpandedCardView(
    navController: NavHostController,
    viewEntity: HomeViewEntity,
    clickAction: (schedule: ScheduleViewEntity) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = "drivers_list"
    ) {
        composable(route = "drivers_list") {
            Row(modifier = Modifier.fillMaxSize()) {
                ShowDriversScreen(
                    viewEntity = viewEntity.copy(highlightSelected = true),
                    maxWidth = 0.5f,
                    clickAction = { schedule ->
                        clickAction(schedule)
                    })
                DriverDetailView(entity = viewEntity.getSelectedSchedule())
            }
        }
    }
}