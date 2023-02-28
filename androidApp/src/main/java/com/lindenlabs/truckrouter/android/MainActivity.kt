package com.lindenlabs.truckrouter.android

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lindenlabs.truckrouter.ResourceReader
import com.lindenlabs.truckrouter.android.ui.MyApplicationTheme
import com.lindenlabs.truckrouter.android.ui.screens.HomeViewModel
import com.lindenlabs.truckrouter.android.ui.screens.show_drivers.ShowDriversScreen
import com.lindenlabs.truckrouter.android.ui.utils.WindowType
import com.lindenlabs.truckrouter.android.ui.utils.rememberWindowSize
import com.lindenlabs.truckrouter.android.ui.views.ExpandedCardView
import com.lindenlabs.truckrouter.android.ui.views.StandardCardView
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
        homeViewModel.data.observe(this) { viewEntity ->
            setContent {
                HomeView(homeViewModel, viewEntity)
            }
        }
    }
}

@Composable
fun HomeView(homeViewModel: HomeViewModel, viewEntity: HomeViewEntity) {
    MyApplicationTheme {
        val window = rememberWindowSize()
        val navController = rememberNavController()
        when {
            window.width == WindowType.Compact -> StandardCardView(navController, viewEntity)
            LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE -> LandscapeCard(navController, viewEntity, homeViewModel)
            else -> LandscapeCard(navController, viewEntity, homeViewModel)
        }
    }
}

@Composable
fun LandscapeCard(navController: NavHostController, viewEntity: HomeViewEntity, homeViewModel: HomeViewModel) {
    ExpandedCardView(navController, viewEntity) { schedule ->
        val index = viewEntity.schedules.indexOf(schedule)
        homeViewModel.data.value = viewEntity.copy(selectedIndex = index)
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
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
            ),
            clickAction = {}
        )
    }
}