package com.uxstate.onboarding_presentation.nutrients_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.core.domain.use_cases.FilterOutDigits
import com.uxstate.core.navigation.Route
import com.uxstate.core.util.UIEvent
import com.uxstate.onboarding_domain.use_case.ValidateNutrients
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientsViewModel @Inject constructor(
    private val prefs: Preferences,
    private val filterOutDigits: FilterOutDigits,
    private val validateNutrients: ValidateNutrients
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

                //validating

                val result = validateNutrients(state.carbsRatio, state.proteinRatio, state.fatRatio)

                when (result) {

                    is ValidateNutrients.Result.Success -> {
                        //save nutrients
                        prefs.saveCarbRatio(result.carbsRatio)
                        prefs.saveProteinRatio(result.proteinsRatio)
                        prefs.saveFatRatio(result.fatsRatio)


                        //navigate

                        viewModelScope.launch {

                            _uiEvent.send(UIEvent.Navigate(route = Route.TRACKER_OVERVIEW))
                        }
                    }

                    //show snackbar
                    is ValidateNutrients.Result.Error -> {

                        viewModelScope.launch {

                            _uiEvent.send(UIEvent.ShowSnackbar(result.message))
                        }

                    }

                }


            }

        }
    }
}