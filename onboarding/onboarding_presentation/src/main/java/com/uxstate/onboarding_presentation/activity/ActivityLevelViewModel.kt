package com.uxstate.onboarding_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.uxstate.core.domain.model.ActivityLevel
import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.core.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class ActivityLevelViewModel @Inject constructor(private val prefs: Preferences) :
    ViewModel() {


    var activityLevel by mutableStateOf<ActivityLevel>(ActivityLevel.Medium)
        
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onSelectActivityLevel(selectedActivityLevel: ActivityLevel) {

        //update activity level
        activityLevel = selectedActivityLevel

    }

}