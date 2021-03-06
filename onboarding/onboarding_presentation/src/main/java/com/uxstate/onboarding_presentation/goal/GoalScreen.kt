package com.uxstate.onboarding_presentation.goal

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
import com.uxstate.core.domain.model.GoalType
import com.uxstate.core.util.UIEvent
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.onboarding_presentation.R
import com.uxstate.onboarding_presentation.components.ActionButton
import com.uxstate.onboarding_presentation.components.SelectableButton
import kotlinx.coroutines.flow.collect

@Composable
fun GoalScreen(
    viewModel: GoalViewModel = hiltViewModel(),
    onNextClick: () -> Unit
) {

    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = true, block = {


        viewModel.uiEvent.collect { event ->

            when(event){

                is UIEvent.Successs -> {
                    onNextClick()
                }
                else -> Unit
            }
        }
    })



   Box(contentAlignment = Alignment.Center,
        modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceLarge),

    ) {


        Column (horizontalAlignment = Alignment.CenterHorizontally){

            Text(
                text = stringResource(id = R.string.lose_keep_or_gain_weight),
                style = MaterialTheme.typography.h3
            )

            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row(
               
            ) {

                SelectableButton(
                    text = stringResource(id = R.string.lose),
                    isSelected = viewModel.goal is GoalType.LoseWeight,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onclick = {

                        viewModel.onSelectGoalType(GoalType.LoseWeight)
                    })

                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton(
                    text = stringResource(id = R.string.keep),
                    isSelected = viewModel.goal is GoalType.KeepWeight,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onclick = { viewModel.onSelectGoalType(GoalType.KeepWeight) })

                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton(
                    text = stringResource(id = R.string.gain),
                    isSelected = viewModel.goal is GoalType.GainWeight,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onclick = { viewModel.onSelectGoalType(GoalType.GainWeight) })

            }


        }

        ActionButton(
            text = stringResource(id = R.string.next),
            onclick = { viewModel.onNextClick() },
            modifier = Modifier.align(Alignment.BottomEnd))
    }

}