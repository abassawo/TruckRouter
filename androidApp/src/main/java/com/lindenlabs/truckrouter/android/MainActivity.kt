package com.lindenlabs.truckrouter.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lindenlabs.truckrouter.ResourceReader
import com.lindenlabs.truckrouter.android.screens.show_drivers.ShowDriversScreen
import com.lindenlabs.truckrouter.android.screens.show_shipment_detail.DriverDetailView
import com.lindenlabs.truckrouter.data.models.RawScheduleResponse
import com.lindenlabs.truckrouter.domain.ScheduleDomainMapper
import com.lindenlabs.truckrouter.presentation.HomeViewEntity
import com.lindenlabs.truckrouter.presentation.ViewMapper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            homeView(viewEntity = homeViewModel.data!!)
        }
    }
}

@Composable
fun homeView(viewEntity: HomeViewEntity) {
    return MyApplicationTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "drivers_list"
        ) {
            val canPop = navController.navigateUp()
            composable(route = "drivers_list") {
                ShowDriversScreen(viewEntity = viewEntity, navController = navController)
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
}

private fun NavBackStackEntry.toDriverIdx(): Int = arguments?.getInt("driverIdx") ?: -1

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        val json = ResourceReader(LocalContext.current).invoke()
        val rawScheduleResponse = Json.decodeFromString<RawScheduleResponse>(json)
        ShowDriversScreen(
            viewEntity = ViewMapper().map(
                ScheduleDomainMapper().invoke(
                    rawScheduleResponse
                )
            ), rememberNavController()
        )
    }
}
