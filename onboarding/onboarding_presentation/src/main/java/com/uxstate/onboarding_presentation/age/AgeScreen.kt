package com.uxstate.onboarding_presentation.age

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.uxstate.core.util.UIEvent
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.onboarding_presentation.R
import com.uxstate.onboarding_presentation.components.ActionButton
import com.uxstate.onboarding_presentation.components.UnitTextField
import kotlinx.coroutines.flow.collect

@Composable
fun AgeScreen(viewModel: AgeViewModel = hiltViewModel(), onNavigate: (UIEvent.Navigate) -> Unit) {

    val spacing = LocalSpacing.current
    //listen to events from ViewModel

    LaunchedEffect(key1 = true, block = {

        viewModel.uiEvents.collect {

                event ->

            when (event) {

                is UIEvent.Navigate -> onNavigate(event)

                is UIEvent.ShowSnackbar -> {}

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

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(id = R.string.whats_your_age),
                style = MaterialTheme.typography.h3
            )
            UnitTextField(
                value = viewModel.age, onValueChange = viewModel::onAgeEnter, unit = stringResource(
                    id = R.string.cm
                )
            )
        }

        //Action Button
        ActionButton(
            text = stringResource(id = R.string.next),
            onclick = viewModel::onClickNext, modifier = Modifier.align(Alignment.BottomEnd)
        )
    }

}