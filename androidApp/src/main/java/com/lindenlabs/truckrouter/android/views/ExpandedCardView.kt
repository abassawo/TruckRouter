package com.lindenlabs.truckrouter.android.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lindenlabs.truckrouter.android.screens.show_drivers.ShowDriversScreen
import com.lindenlabs.truckrouter.presentation.HomeViewEntity

@Composable
fun ExpandedCardView(navController: NavHostController, viewEntity: HomeViewEntity) {
    NavHost(
        navController = navController,
        startDestination = "drivers_list"
    ) {
        composable(route = "drivers_list") {
            ShowDriversScreen(viewEntity = viewEntity, navController = navController, occupyMaxWidth = false)
        }
    }
}