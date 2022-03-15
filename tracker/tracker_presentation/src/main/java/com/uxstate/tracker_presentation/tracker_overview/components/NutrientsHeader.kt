package com.uxstate.tracker_presentation.tracker_overview.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.tracker_presentation.R
import com.uxstate.tracker_presentation.components.UnitDisplay
import com.uxstate.tracker_presentation.tracker_overview.TrackerOverviewState


@Composable
fun NutrientsHeader(state: TrackerOverviewState, modifier: Modifier) {

    val spacing = LocalSpacing.current
    val animatedCaloriesCount by animateIntAsState(targetValue = state.totalCalories)

    Column(
        modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp))
                .background(MaterialTheme.colors.primary)
                .padding(horizontal = spacing.spaceLarge, vertical = spacing.spaceExtraLarge)
    ) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

            UnitDisplay(
                amount = animatedCaloriesCount,
                unit = stringResource(id = R.string.kcal),
                modifier = Modifier.align(Alignment.Bottom),
                amountColor = MaterialTheme.colors.onPrimary,
                amountTextSize = 40.sp,
                unitColor = MaterialTheme.colors.onPrimary
            )

            Column() {
                Text(
                    text = stringResource(id = R.string.your_goal),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.align(Alignment.Start)
                )

                UnitDisplay(
                    amount = state.caloriesGoal,
                    unit = stringResource(id = R.string.kcal),
                    modifier = Modifier.align(Alignment.Start),
                    amountColor = MaterialTheme.colors.onPrimary,
                    amountTextSize = 40.sp,
                    unitColor = MaterialTheme.colors.onPrimary
                )
            }
        }

        Spacer(modifier = Modifier.height(spacing.spaceSmall))

        NutrientsBar(
            carbs = state.totalCarbs,
            proteins = state.totalProteins,
            fats = state.totalFats,
            calories = state.totalCalories,
            caloriesGoal = state.caloriesGoal,
            modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
        )
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