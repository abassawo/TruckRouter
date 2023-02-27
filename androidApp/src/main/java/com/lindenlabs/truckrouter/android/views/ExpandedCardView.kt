package com.lindenlabs.truckrouter.android.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lindenlabs.truckrouter.android.HomeViewModel
import com.lindenlabs.truckrouter.android.screens.show_drivers.ShowDriversScreen
import com.lindenlabs.truckrouter.android.screens.show_shipment_detail.DriverDetailView

@Composable
fun ExpandedCardView(navController: NavHostController, viewModel: HomeViewModel) {
    val schedules = viewModel.data?.schedules ?: emptyList()
    NavHost(
        navController = navController,
        startDestination = "drivers_list"
    ) {

        composable(route = "drivers_list") {
            Row(modifier = Modifier.fillMaxSize()) {
                viewModel.data?.let { viewEntity ->
                    val updatedViewEntity = viewEntity.copy(highlightSelected = true)
                    ShowDriversScreen(
                        viewEntity = updatedViewEntity,
                        maxWidth = 0.5f,
                        clickAction = { schedule ->
                            updatedViewEntity.selectedIndex = schedules.indexOf(schedule)
                            // todo - have the viewmodel represent the view state
                        })
                    DriverDetailView(entity = updatedViewEntity.getSelectedSchedule(), maxWidth = 0.7f)
                }
            }
        }
    }
}