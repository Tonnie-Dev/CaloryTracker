package com.uxstate.tracker_presentation.tracker_overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.core.navigation.Route
import com.uxstate.core.util.UIEvent
import com.uxstate.tracker_domain.use_cases.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
    private val trackerUseCases: TrackerUseCases,
    private val prefs: Preferences
) :
    ViewModel() {

    //STATES
    var state by mutableStateOf(TrackerOverviewState())
        private set

    //EVENTS
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {

        //make the onboarding not show again
        prefs.saveShouldShowOnboarding(false)


    }


    fun onEvent(event: TrackerOverViewEvent) {


        //propagate state to the UI
        when (event) {

            is TrackerOverViewEvent.OnAddFoodClick -> {

                //navigate to search screen with the given meal and date
                //we cannot pass a local date on navigation therefore we pass it as a String
                viewModelScope.launch {

                    _uiEvent.send(
                        UIEvent.Navigate(
                            Route.SEARCH +
                                    "/${event.meal.mealType.name}" +
                                    "/${state.date.dayOfMonth}" +
                                    "/${state.date.monthValue}" +
                                    "/${state.date.year}"
                        )
                    )
                }

            }
            is TrackerOverViewEvent.OnDeleteTrackedFoodClick -> {

                //
            }
            is TrackerOverViewEvent.OnNextDayClick -> {}
            is TrackerOverViewEvent.OnToggleMealClick -> {}
            is TrackerOverViewEvent.OnPreviousDayClick -> {}

        }

    }
}