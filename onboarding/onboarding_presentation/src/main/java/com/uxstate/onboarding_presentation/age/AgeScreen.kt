package com.uxstate.onboarding_presentation.age

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
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
import com.uxstate.core.util.UiText
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.onboarding_presentation.R
import com.uxstate.onboarding_presentation.components.ActionButton
import com.uxstate.onboarding_presentation.components.UnitTextField
import kotlinx.coroutines.flow.collect

@Composable
fun AgeScreen(
    scaffoldState: ScaffoldState,
    viewModel: AgeViewModel = hiltViewModel(),
    onNextClick: () -> Unit
) {

    val spacing = LocalSpacing.current
    //context for unwrapping UiText class
    val context = LocalContext.current

    //listen to events from ViewModel

    LaunchedEffect(key1 = true, block = {

        viewModel.uiEvents.collect {

                event ->

            when (event) {

                is UIEvent.Navigate -> onNextClick()

                is UIEvent.ShowSnackbar -> {

                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context = context)
                    )
                }

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
                text = UiText.StringResource(R.string.whats_your_age)
                        .asString(context = context),
                style = MaterialTheme.typography.h3
            )
            UnitTextField(
                value = viewModel.age, onValueChange = viewModel::onAgeEnter, unit = stringResource(
                    id = R.string.years
                )
            )
        }

        //Action Button
        ActionButton(
            text = UiText.StringResource(R.string.next)
                    .asString(context = context),
            onclick = viewModel::onClickNext, modifier = Modifier.align(Alignment.BottomEnd)
        )
    }

}