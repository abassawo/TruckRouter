package com.lindenlabs.truckrouter.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.lindenlabs.truckrouter.ResourceReader
import com.lindenlabs.truckrouter.android.screens.show_drivers.ShowDriversScreen
import com.lindenlabs.truckrouter.android.views.ExpandedCardView
import com.lindenlabs.truckrouter.android.views.StandardCardView
import com.lindenlabs.truckrouter.data.models.RawScheduleResponse
import com.lindenlabs.truckrouter.domain.ScheduleDomainMapper
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
            homeView(viewModel = homeViewModel)
        }
    }
}

@Composable
fun homeView(viewModel: HomeViewModel) {
    return MyApplicationTheme {
        val window = rememberWindowSize()
        val navController = rememberNavController()
        when (window.width) {
            WindowType.Compact -> viewModel.data?.let { StandardCardView(navController, it) }
            else -> ExpandedCardView(navController, viewModel)
        }
    }
}

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
            ),
            clickAction = {}
        )
    }
}
