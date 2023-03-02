package com.lindenlabs.desktop

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.lindenlabs.truckrouter.presentation.HomeViewEntity
import com.lindenlabs.truckrouter.presentation.ScheduleViewEntity

val VioletHex = Color(0xffcf94da)
val lightThemeColors = lightColors(
    primary = Color(0xFFDD0D3C),
    primaryVariant = Color(0xFFC20029),
    secondary = Color.White,
    error = Color(0xFFD00036)
)

fun main() = application {
    val windowState = rememberWindowState()
    val viewModel = remember { HomeViewModel() }

    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        title = "Truck Router"
    ) {
        MaterialTheme(colors = lightThemeColors) {
            with(viewModel) {
                when (viewModel.results.value) {
                    is HomeViewEntity.Content -> LandscapeCard(results.value, viewModel)
                    HomeViewEntity.Error -> TODO()
                    HomeViewEntity.Loading -> TODO()
                }
            }

        }
    }
}

@Composable
fun LandscapeCard(viewEntity: HomeViewEntity, viewModel: HomeViewModel) {
    ExpandedCardView(viewEntity, viewModel)
}

@Composable
fun ExpandedCardView(
    viewEntity: HomeViewEntity,
    viewModel: HomeViewModel
) {
    when (viewEntity) {
        HomeViewEntity.Error -> TODO()
        HomeViewEntity.Loading -> TODO()
        is HomeViewEntity.Content -> Row {
            StandardCardView(
                viewEntity = viewEntity.copy(
                    highlightSelected = true,
                    maxCardWidth = 0.3f
                ), clickAction = { schedule ->
                    val index = viewEntity.schedules.indexOf(schedule)
                    viewModel.onIndexChanged(index)
                }
            )
            DriverDetailView(entity = viewEntity.getSelectedSchedule())
        }
    }
}

@Composable
fun StandardCardView(
    viewEntity: HomeViewEntity,
    clickAction: (schedule: ScheduleViewEntity) -> Unit
) {
    Box {
        ShowDriversScreen(viewEntity = viewEntity, clickAction = clickAction)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShowDriversScreen(
    viewEntity: HomeViewEntity,
    maxWidth: Float = viewEntity.maxCardWidth,
    clickAction: (schedule: ScheduleViewEntity) -> Unit,
) {
    when (viewEntity) {
        is HomeViewEntity.Content -> {
            Scaffold(modifier = Modifier.fillMaxWidth(maxWidth)) { padding ->
                Column(
                    modifier =
                    Modifier
                        .padding(padding)
                        .fillMaxWidth()
                ) {
                    this@Column.AnimatedVisibility(
                        visible = true,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        TopAppBar(
                            backgroundColor = Color.Transparent,
                            title = { Text(text = viewEntity.headerText) }
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(PaddingValues(start = 16.dp))
                            .fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        this@Column.AnimatedVisibility(
                            visible = true,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            Text(
                                modifier = Modifier.padding(0.dp, 8.dp),
                                text = "Total Suitability  " + viewEntity.totalSuitability,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp
                            )
                        }
                    }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        items(
                            items = viewEntity.schedules,
                            key = { it.driverName }
                        ) { schedule ->
                            DriverItemView(
                                schedule = schedule,
                                backgroundColor = when {
                                    viewEntity.highlightSelected -> if (schedule == viewEntity.getSelectedSchedule()) VioletHex else Color.White
                                    else -> Color.White
                                },
                                onDriverClick = {
                                    clickAction(schedule)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .animateItemPlacement()
                            )
                            Divider(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(2.dp)
                            )
                        }
                    }

                }
            }
        }
        else -> {}
    }
}


@Composable
fun DriverItemView(
    schedule: ScheduleViewEntity,
    backgroundColor: Color,
    onDriverClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color.LightGray)
            .clip(RoundedCornerShape(5.dp))
            .background(backgroundColor)
            .clickable { onDriverClick() }
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = schedule.driverName,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = schedule.destinationAddress,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold
        )
    }
}
