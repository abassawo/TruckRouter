package com.lindenlabs.truckrouter.android.ui.views

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.lindenlabs.truckrouter.android.ui.screens.HomeViewModel
import com.lindenlabs.truckrouter.android.ui.screens.show_shipment_detail.DriverDetailView
import com.lindenlabs.truckrouter.presentation.HomeViewEntity

/*
   Composite list and detail view that may best be use on larger devices
 */
@Composable
fun ExpandedCardView(
    navController: NavHostController,
    viewEntity: HomeViewEntity,
    viewModel: HomeViewModel
) {
    Row {
        StandardCardView(
            navController = navController,
            viewEntity = viewEntity.copy(
                highlightSelected = true,
                maxCardWidth = 0.3f
            ), clickAction = { schedule ->
                val index = viewEntity.schedules.indexOf(schedule)
                viewModel.onIndexChanged(index)
            }
        )
        DriverDetailView(entity = viewEntity.getSelectedSchedule(), isLandscape = true)
    }
}