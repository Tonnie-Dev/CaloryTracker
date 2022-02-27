package com.uxstate.onboarding_presentation.nutrients_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.core.domain.use_cases.FilterOutDigits
import com.uxstate.core.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientsViewModel @Inject constructor(
    private val prefs: Preferences,
    private val filterOutDigits: FilterOutDigits
) :
    ViewModel() {

    //state
    var state by mutableStateOf(NutrientsGoalState())
        private set

    //channel
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: NutrientGoalEvents) {

        when (event) {

            is NutrientGoalEvents.OnCarbsRatioEnter -> {

                state = state.copy(
                    carbsRatio = filterOutDigits(event.ratio)
                )
            }
            is NutrientGoalEvents.OnProteinsRatioEnter -> {

                state = state.copy(
                    proteinRatio = filterOutDigits(event.ratio)
                )
            }
            is NutrientGoalEvents.OnFatsRatioEnter -> {
                state = state.copy(
                    fatRatio = filterOutDigits(event.ratio)
                )
            }
            is NutrientGoalEvents.OnNextClick -> {

                viewModelScope.launch {


                }
            }

        }
    }
}