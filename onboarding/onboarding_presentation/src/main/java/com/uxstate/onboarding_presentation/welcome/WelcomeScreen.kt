package com.uxstate.onboarding_presentation.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.uxstate.core.R
import com.uxstate.core.util.UIEvent
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.onboarding_presentation.components.ActionButton

@Composable
fun WelcomeScreen(onNavigate: (UIEvent.Navigate) -> Unit) {

    val spacing = LocalSpacing.current


    Column(
        modifier = Modifier.fillMaxSize().padding(spacing.spaceMedium),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {






        Text(
            text = stringResource(id = R.string.welcome_text),
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Center
        )



        Spacer(modifier = Modifier.height(spacing.spaceMedium))


        ActionButton(
            text = stringResource(id = R.string.next),
            onclick = { }, modifier = Modifier.align(CenterHorizontally)
        )
    }
}

