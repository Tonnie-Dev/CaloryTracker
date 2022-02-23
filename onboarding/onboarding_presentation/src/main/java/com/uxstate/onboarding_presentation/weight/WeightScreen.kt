package com.uxstate.onboarding_presentation.weight

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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

@Composable
fun WeightScreen(
    viewModel: WeightViewModel = hiltViewModel(),
    onNavigate: (UIEvent.Navigate) -> Unit
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {


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


        ActionButton(text = stringResource(id = R.string.next), onclick = viewModel::onNextClick)
    }
}