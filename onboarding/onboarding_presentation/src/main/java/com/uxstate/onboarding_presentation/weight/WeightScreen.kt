package com.uxstate.onboarding_presentation.weight

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
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.onboarding_presentation.R
import com.uxstate.onboarding_presentation.components.ActionButton
import com.uxstate.onboarding_presentation.components.UnitTextField
import kotlinx.coroutines.flow.collect

@Composable
fun WeightScreen(
    viewModel: WeightViewModel = hiltViewModel(),
    onNextClick: () -> Unit,
    scaffoldState: ScaffoldState
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current

    //LaunchedEffect to listen to events from ViewModel

    LaunchedEffect(key1 = true, block = {

        viewModel.uiEvent.collect { event ->


            when (event) {

                is UIEvent.Successs -> {

                    onNextClick()
                }
                is UIEvent.ShowSnackbar -> {

                    scaffoldState.snackbarHostState.showSnackbar(event.message.asString(context = context))

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
                text = stringResource(id = R.string.whats_your_weight),
                style = MaterialTheme.typography.h3
            )

            UnitTextField(
                value = viewModel.weight,
                onValueChange = viewModel::onEnterWeight,
                unit = stringResource(id = R.string.kg)
            )
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