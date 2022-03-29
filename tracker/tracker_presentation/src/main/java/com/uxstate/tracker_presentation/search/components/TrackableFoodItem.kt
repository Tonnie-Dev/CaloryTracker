package com.uxstate.tracker_presentation.search.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.tracker_presentation.search.TrackableFoodUiState


@Composable
fun TrackableFoodItem(
    trackableFoodUiState: TrackableFoodUiState,
    onClick: () -> Unit,
    onAmountChange: (String) -> Unit,
    onTrack:() -> Unit, modifier: Modifier = Modifier
){

    val food = trackableFoodUiState.food
    val spacing = LocalSpacing.current

}