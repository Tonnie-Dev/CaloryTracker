package com.uxstate.onboarding_presentation.goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.uxstate.core.domain.model.GoalType
import com.uxstate.core.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import java.util.prefs.Preferences
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(private val prefs: Preferences) :
    ViewModel() {

    var goal by mutableStateOf<GoalType>(GoalType.KeepWeight)
        private set

    private val _uiState = Channel<UIEvent>()


}