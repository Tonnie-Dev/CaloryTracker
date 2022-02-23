package com.uxstate.onboarding_presentation.activity

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.uxstate.core.domain.model.ActivityLevel
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.onboarding_presentation.ActivityLevelViewModel
import com.uxstate.onboarding_presentation.R
import com.uxstate.onboarding_presentation.components.ActionButton
import com.uxstate.onboarding_presentation.components.SelectableButton


@Composable
fun ActivityLevelScreen(viewModel: ActivityLevelViewModel = hiltViewModel()) {
    val spacing = LocalSpacing.current
    Box(
        modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceLarge), contentAlignment = Alignment.Center
    ) {


        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(id = R.string.whats_your_activity_level),
                style = MaterialTheme.typography.h3
            )

            Row(horizontalArrangement = Arrangement.SpaceBetween) {

                SelectableButton(
                    text = stringResource(id = R.string.low),
                    isSelected = viewModel.activityLevel is ActivityLevel.Low,
                    color =MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onclick = {

                        viewModel.onSelectActivityLevel(ActivityLevel.Low)
                    })

                SelectableButton(
                    text = stringResource(id = R.string.medium),
                    isSelected = viewModel.activityLevel is ActivityLevel.Medium,
                    color =MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onclick = { viewModel.onSelectActivityLevel(ActivityLevel.Medium) })

                SelectableButton(
                    text = stringResource(id = R.string.high),
                    isSelected = viewModel.activityLevel is ActivityLevel.High,
                    color =MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onclick = { viewModel.onSelectActivityLevel(ActivityLevel.High)})

            }
        }

        ActionButton(text = stringResource(id = R.string.next), onclick = viewModel::onNextClick)


    }

}