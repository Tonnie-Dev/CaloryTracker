package com.uxstate.tracker_presentation.tracker_overview

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.uxstate.core.util.UIEvent
import com.uxstate.core_ui.LocalSpacing

@Composable
fun TrackerScreenOverview(
    onNavigate: (UIEvent.Navigate) -> Unit,
    viewModel: TrackerOverviewViewModel = hiltViewModel()
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val state = viewModel.state
}