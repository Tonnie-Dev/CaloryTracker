package com.uxstate.onboarding_presentation.height

import androidx.compose.foundation.layout.*
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
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.onboarding_presentation.R
import com.uxstate.onboarding_presentation.components.ActionButton
import com.uxstate.onboarding_presentation.components.UnitTextField
import kotlinx.coroutines.flow.collect

@Composable
fun HeightScreen(
    viewModel: HeightViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    onNavigate: (UIEvent.Navigate) -> Unit
) {

    val context = LocalContext.current
    val spacing = LocalSpacing.current

    //listens to events from ViewModel using LaunchedEffect block

    LaunchedEffect(key1 = true, block = {

        viewModel.uiEvent.collect { event ->

            when (event) {


                is UIEvent.Navigate -> {

                    onNavigate(event)

                }
                is UIEvent.ShowSnackbar -> {

                    //this events carries ShowSnackbar Event which only need to be unboxed
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(
                            context = context
                        )
                    )
                }
                else -> Unit
            }
        }

    })

    Box(
        modifier = Modifier.fillMaxSize().padding(spacing.spaceMedium),
        contentAlignment = Alignment.Center
       )
     {


        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(id = R.string.whats_your_height),
                style = MaterialTheme.typography.h3
            )

            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            UnitTextField(
                value = viewModel.height,
                onValueChange = viewModel::onEnterHeight,
                unit = stringResource(id = R.string.cm)
            )

        }

        ActionButton(
            text = stringResource(id = R.string.next),
            onclick = viewModel::onClickNext,
            modifier = Modifier.align(
                Alignment.BottomEnd
            )
        )
    }
}