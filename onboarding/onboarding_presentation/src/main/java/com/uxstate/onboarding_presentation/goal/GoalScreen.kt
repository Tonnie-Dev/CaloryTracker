package com.uxstate.onboarding_presentation.goal

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.uxstate.core.util.UIEvent
import com.uxstate.core_ui.LocalSpacing

@Composable
fun GoalScreen(
    viewModel: GoalViewModel = hiltViewModel(),
    onNavigate: (UIEvent.Navigate) -> Unit
) {

    val spacing = LocalSpacing.current

    Box(
        modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceLarge), contentAlignment = Alignment.Center
    ) {


        
    }

}