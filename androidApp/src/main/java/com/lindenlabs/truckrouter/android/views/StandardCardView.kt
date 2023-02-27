package com.lindenlabs.truckrouter.android.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lindenlabs.truckrouter.android.screens.show_drivers.ShowDriversScreen
import com.lindenlabs.truckrouter.android.screens.show_shipment_detail.DriverDetailView
import com.lindenlabs.truckrouter.presentation.HomeViewEntity


@Composable
fun StandardCardView(navController: NavHostController, viewEntity: HomeViewEntity) {
    NavHost(
        navController = navController,
        startDestination = "drivers_list"
    ) {
        composable(route = "drivers_list") {
            ShowDriversScreen(viewEntity = viewEntity, clickAction = { schedule ->
                navController.navigate(
                    "schedule_detail/${viewEntity.schedules.indexOf(schedule)}"
                )
            })
        }
        composable(
            route = "schedule_detail/{driverIdx}",
            arguments = listOf(
                navArgument(name = "driverIdx") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            val index = backStackEntry.toDriverIdx()
            val schedule = viewEntity.schedules[index]
            DriverDetailView(
                entity = schedule,
                navController = navController
            )
        }
    }
}

fun NavBackStackEntry.toDriverIdx(): Int = arguments?.getInt("driverIdx") ?: -1
