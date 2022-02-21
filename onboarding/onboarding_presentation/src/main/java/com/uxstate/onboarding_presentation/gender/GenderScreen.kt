package com.uxstate.onboarding_presentation.gender

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.uxstate.core.domain.model.Gender
import com.uxstate.core.util.UIEvent
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.onboarding_presentation.R
import com.uxstate.onboarding_presentation.components.ActionButton
import com.uxstate.onboarding_presentation.components.SelectableButton
import kotlinx.coroutines.flow.collect

@Composable
fun GenderScreen(
    viewModel: GenderViewModel = hiltViewModel(),
    onNavigate: (UIEvent.Navigate) -> Unit
) {

    val spacing = LocalSpacing.current
    val isMaleSelected = viewModel.selectedGender is Gender.Male
    val isFemaleSelected = viewModel.selectedGender is Gender.Female
    //use LaunchedEffect to collect events from the ViewModel

    LaunchedEffect(key1 = true, block = {

        viewModel.uiEvent.collect {

                event ->

            when (event) {

                is UIEvent.Navigate -> {

                    onNavigate(event)
                }

                //ignore
                else -> Unit
            }
        }
    })


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceLarge)
    ) {


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = stringResource(id = R.string.whats_your_gender),
                style = MaterialTheme.typography.h3
            )

            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row() {
                SelectableButton(
                    text = stringResource(id = R.string.male),
                    isSelected = isMaleSelected,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onclick = {

                        viewModel.onGenderClick(Gender.Male)
                    },
                    textStyle = MaterialTheme.typography.button.copy(fontWeight = FontWeight.Normal)
                )



                SelectableButton(
                    text = stringResource(id = R.string.female),
                    isSelected = isFemaleSelected,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onclick = {

                        viewModel.onGenderClick(Gender.Female)
                    },
                    textStyle = MaterialTheme.typography.button.copy(fontWeight = FontWeight.Normal)
                )
            }
        }

        ActionButton(
            text = stringResource(id = R.string.next),
            onclick = viewModel::onNextClick,
            Modifier.align(
                Alignment.BottomEnd
            )
        )
    }
}