package com.uxstate.onboarding_presentation.height

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.core.domain.use_cases.FilterOutDigits
import com.uxstate.core.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(private val prefs:Preferences, useCase:FilterOutDigits) :
    ViewModel() {


        var height by mutableStateOf("180")
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

}