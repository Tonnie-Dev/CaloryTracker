package com.uxstate.onboarding_presentation.height

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.uxstate.onboarding_presentation.R

@Composable
fun HeightScreen(viewModel: HeightViewModel = hiltViewModel(), scaffoldState: ScaffoldState) {


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {


        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(id = R.string.whats_your_height),
                style = MaterialTheme.typography.h3
            )
        }
    }
}