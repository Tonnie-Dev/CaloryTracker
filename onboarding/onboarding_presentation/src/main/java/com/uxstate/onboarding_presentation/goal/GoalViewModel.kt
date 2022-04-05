package com.uxstate.onboarding_presentation.goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.core.domain.model.GoalType
import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.core.navigation.Route
import com.uxstate.core.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(private val prefs: Preferences) :
    ViewModel() {

    var goal by mutableStateOf<GoalType>(GoalType.KeepWeight)
        private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onSelectGoalType(goalType:GoalType){

        goal = goalType
    }


    fun onNextClick(){

        viewModelScope.launch {

            //save goal type

            prefs.saveGoalType(goal)
            //navigate

            _uiEvent.send(UIEvent.Successs(route = Route.NUTRIENT_GOAL))
        }
    }

}