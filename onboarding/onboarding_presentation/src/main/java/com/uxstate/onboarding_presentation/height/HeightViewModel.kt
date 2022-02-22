package com.uxstate.onboarding_presentation.height

import androidx.lifecycle.ViewModel
import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.core.domain.use_cases.FilterOutDigits
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(private val prefs:Preferences, useCase:FilterOutDigits) :
    ViewModel() {
}