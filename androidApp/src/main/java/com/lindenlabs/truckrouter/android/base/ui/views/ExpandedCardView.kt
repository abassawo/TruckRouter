package com.lindenlabs.truckrouter.android.base.ui.views

import android.telecom.Call.Details
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
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
    Row {
        StandardCardView(
            navController = navController,
            viewEntity = viewEntity.copy(
                highlightSelected = true,
                maxCardWidth = 0.3f
            ), clickAction = clickAction
        )
        DriverDetailView(entity = viewEntity.getSelectedSchedule(), isLandscape = true)
    }
}