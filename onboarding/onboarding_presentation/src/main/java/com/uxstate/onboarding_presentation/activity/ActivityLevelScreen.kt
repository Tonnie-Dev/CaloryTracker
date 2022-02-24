package com.uxstate.onboarding_presentation.activity

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.uxstate.core.domain.model.ActivityLevel
import com.uxstate.core.util.UIEvent
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.onboarding_presentation.ActivityLevelViewModel
import com.uxstate.onboarding_presentation.R
import com.uxstate.onboarding_presentation.components.ActionButton
import com.uxstate.onboarding_presentation.components.SelectableButton
import kotlinx.coroutines.flow.collect


@Composable
fun ActivityLevelScreen(
    viewModel: ActivityLevelViewModel = hiltViewModel(),
    onNavigate: (UIEvent.Navigate) -> Unit
) {

    val spacing = LocalSpacing.current


    //listen to events from ViewModel

    LaunchedEffect(key1 = true, block = {


        viewModel.uiEvent.collect { event ->

            when (event) {

                is UIEvent.Navigate -> {

                    onNavigate(event)
                }
                else -> Unit
            }
        }

    })



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

            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row() {

                SelectableButton(
                    text = stringResource(id = R.string.low),
                    isSelected = viewModel.activityLevel is ActivityLevel.Low,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onclick = {

                        viewModel.onSelectActivityLevel(ActivityLevel.Low)
                    })

                Spacer(modifier = Modifier.width(spacing.spaceMedium))

                SelectableButton(
                    text = stringResource(id = R.string.medium),
                    isSelected = viewModel.activityLevel is ActivityLevel.Medium,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onclick = { viewModel.onSelectActivityLevel(ActivityLevel.Medium) })

                Spacer(modifier = Modifier.width(spacing.spaceMedium))

                SelectableButton(
                    text = stringResource(id = R.string.high),
                    isSelected = viewModel.activityLevel is ActivityLevel.High,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onclick = { viewModel.onSelectActivityLevel(ActivityLevel.High) })

            }
        }

        ActionButton(
            text = stringResource(id = R.string.next),
            onclick = viewModel::onNextClick,
            modifier = Modifier.align(
                Alignment.BottomEnd
            )
        )


    }

}