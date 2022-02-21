package com.uxstate.onboarding_presentation.age

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.uxstate.onboarding_presentation.R
import com.uxstate.onboarding_presentation.components.ActionButton
import com.uxstate.onboarding_presentation.components.UnitTextField

@Composable
fun AgeScreen(viewModel: AgeViewModel = hiltViewModel()) {
    //listen to

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {

        Column {
            Text(text = stringResource(id = R.string.whats_your_age))
            UnitTextField(
                value = viewModel.age, onValueChange = viewModel::onAgeEnter, unit = stringResource(
                    id = R.string.cm
                )
            )
        }

        //Action Button
        ActionButton(text = stringResource(id = R.string.next),
            onclick = viewModel::onClickNext)
    }

}