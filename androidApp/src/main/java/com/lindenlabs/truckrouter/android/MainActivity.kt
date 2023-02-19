package com.lindenlabs.truckrouter.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lindenlabs.truckrouter.data.models.RawScheduleResponse
import com.lindenlabs.truckrouter.domain.DriverDomainEntity
import com.lindenlabs.truckrouter.domain.ScheduleDomainMapper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RootView(driverDomainEntities = homeViewModel.data)
        }
    }
}

@Composable
fun RootView(driverDomainEntities: Set<DriverDomainEntity>) {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            LazyColumn(
                modifier = Modifier
            ) {
                itemsIndexed(items = driverDomainEntities.toList()) { index, item ->
                    GreetingView(text = item.name)
                }
            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        val data = """
            {
              "shipments": [
                "215 Osinski Manors",
                "9856 Marvin Stravenue",
                "7127 Kathlyn Ferry",
                "987 Champlin Lake",
                "63187 Volkman Garden Suite 447",
                "75855 Dessie Lights",
                "1797 Adolf Island Apt. 744",
                "2431 Lindgren Corners",
                "8725 Aufderhar River Suite 859",
                "79035 Shanna Light Apt. 322"
              ],
              "drivers": [
                "Everardo Welch",
                "Orval Mayert",
                "Howard Emmerich",
                "Izaiah Lowe",
                "Monica Hermann",
                "Ellis Wisozk",
                "Noemie Murphy",
                "Cleve Durgan",
                "Murphy Mosciski",
                "Kaiser Sose"
              ]
            }
        """.trimIndent()
        val rawScheduleResponse = Json.decodeFromString<RawScheduleResponse>(data)
        RootView(driverDomainEntities = ScheduleDomainMapper().invoke(rawScheduleResponse))
    }
}
