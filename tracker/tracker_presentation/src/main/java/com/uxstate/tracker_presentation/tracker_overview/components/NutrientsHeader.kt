package com.uxstate.tracker_presentation.tracker_overview.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.tracker_presentation.tracker_overview.TrackerOverviewState


@Composable
fun NutrientsHeader(state: TrackerOverviewState, modifier: Modifier) {

    val spacing = LocalSpacing.current
    val animatedCaloriesCount = animateIntAsState(targetValue = state.totalCalories)

    Column(
        modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
                .background(MaterialTheme.colors.primary)
                .padding(horizontal = spacing.spaceLarge, vertical = spacing.spaceExtraLarge)
    ) {

        Row() {
            
        }
    }


}


@Preview
@Composable
fun MyTest() {
    val spacing = LocalSpacing.current

    Column(
        modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .background(MaterialTheme.colors.primary)
                .clip(
                    RoundedCornerShape(
                        bottomEnd = spacing.spaceExtraLarge,
                        bottomStart = spacing.spaceLarge
                    )
                )
    ) {

    }
}