package com.uxstate.onboarding_presentation.nutrients_goal

import androidx.compose.foundation.layout.*
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.uxstate.core.util.UIEvent
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.onboarding_presentation.R
import com.uxstate.onboarding_presentation.components.ActionButton
import com.uxstate.onboarding_presentation.components.UnitTextField
import kotlinx.coroutines.flow.collect

@Composable
fun NutrientsScreen(
    viewModel: NutrientsViewModel = hiltViewModel(),
    onNavigate: (UIEvent.Navigate) -> Unit,
    scaffoldState: ScaffoldState
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current
    Box(
        modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceMedium), contentAlignment = Alignment.Center
    ) {


        LaunchedEffect(key1 = true, block = {


            viewModel.uiEvent.collect { event ->


                when (event) {


                    is UIEvent.Navigate -> {

                        onNavigate(event)
                    }

                    is UIEvent.ShowSnackbar -> {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = event.message.asString(
                                context
                            )
                        )

                    }
                    else -> Unit
                }
            }
        })



        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.what_are_your_nutrient_goals))
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = viewModel.state.carbsRatio,
                onValueChange = {

                    viewModel.onEvent(NutrientGoalEvents.OnCarbsRatioEnter(it)) },
                unit = stringResource(
                    id = R.string.percent_carbs
                )
            )

            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = viewModel.state.proteinRatio,
                onValueChange = {

                    viewModel.onEvent(NutrientGoalEvents.OnProteinsRatioEnter(it)) },
                unit = stringResource(
                    id = R.string.percent_proteins
                )
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = viewModel.state.fatRatio,
                onValueChange = {

                    viewModel.onEvent(NutrientGoalEvents.OnFatsRatioEnter(it)) },
                unit = stringResource(
                    id = R.string.percent_fats
                )
            )
        }


        ActionButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            text = stringResource(id = R.string.next),
            onclick = {

                viewModel.onEvent(NutrientGoalEvents.OnNextClick)
            }
        )


    }


}