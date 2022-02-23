package com.uxstate.onboarding_presentation.activity

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.uxstate.core_ui.LocalSpacing


@Composable
fun ActivityLevelScreen() {
    val spacing = LocalSpacing.current
    Box(modifier = Modifier.fillMaxSize().padding(spacing.spaceLarge), contentAlignment = Alignment.Center){}

}