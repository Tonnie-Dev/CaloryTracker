package com.uxstate.onboarding_presentation.activity

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.onboarding_presentation.R


@Composable
fun ActivityLevelScreen() {
    val spacing = LocalSpacing.current
    Box(
        modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceLarge), contentAlignment = Alignment.Center
    ) {


        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(id = R.string.whats_your_activity_level),
                style = MaterialTheme.typography.h3
            )
        }
    }

}