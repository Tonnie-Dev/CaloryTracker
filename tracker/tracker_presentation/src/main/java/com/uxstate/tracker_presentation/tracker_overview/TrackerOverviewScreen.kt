package com.uxstate.tracker_presentation.tracker_overview

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.uxstate.core.util.UIEvent
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.tracker_presentation.tracker_overview.components.DaySelector
import com.uxstate.tracker_presentation.tracker_overview.components.NutrientsHeader

@Composable
fun TrackerOverviewScreen(
    onNavigate: (UIEvent.Navigate) -> Unit,
    viewModel: TrackerOverviewViewModel = hiltViewModel()
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val state = viewModel.state

    LazyColumn(
        modifier = Modifier
                .fillMaxSize()
                .padding(bottom = spacing.spaceMedium),
        content = {


            item {

                NutrientsHeader(
                    state = state, modifier = Modifier

                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                DaySelector(
                    localDate = state.date,
                    onPreviousDayClick = {

                                         viewModel.onEvent(TrackerOverViewEvent.OnPreviousDayClick)
                    },
                    onNextDayClick = {
                        viewModel.onEvent(TrackerOverViewEvent.OnNextDayClick)

                    },
                    modifier = Modifier.fillMaxWidth().padding(spacing.spaceMedium)
                )


                Spacer(modifier = Modifier.height(spacing.spaceMedium))
            }


        })


}