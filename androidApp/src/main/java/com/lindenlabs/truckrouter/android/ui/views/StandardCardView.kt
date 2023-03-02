package com.lindenlabs.truckrouter.android.ui.views

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.lindenlabs.truckrouter.android.ui.screens.show_drivers.ShowDriversScreen
import com.lindenlabs.truckrouter.android.ui.screens.show_shipment_detail.DriverDetailView
import com.lindenlabs.truckrouter.presentation.HomeViewEntity
import com.lindenlabs.truckrouter.presentation.ScheduleViewEntity


@Composable
fun StandardCardView(
    navController: NavHostController,
    viewEntity: HomeViewEntity,
    clickAction: (schedule: ScheduleViewEntity) -> Unit = { schedule ->
        if (viewEntity is HomeViewEntity.Content) {
            navController.navigate(
                "schedule_detail/${viewEntity.schedules.indexOf(schedule)}"
            )
        }
    }
) {
    NavHost(
        navController = navController,
        startDestination = "drivers_list"
    ) {
        composable(route = "drivers_list") {
            ShowDriversScreen(viewEntity = viewEntity, clickAction = clickAction)
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
            when(viewEntity) {
                HomeViewEntity.Error -> TODO()
                HomeViewEntity.Loading -> TODO()
                is HomeViewEntity.Content -> {
                    val index = backStackEntry.toDriverIdx()
                    val schedule = viewEntity.schedules[index]
                    DriverDetailView(
                        entity = schedule,
                        navController = navController
                    )
                }
            }
        }
    }
}

fun NavBackStackEntry.toDriverIdx(): Int = arguments?.getInt("driverIdx") ?: -1
