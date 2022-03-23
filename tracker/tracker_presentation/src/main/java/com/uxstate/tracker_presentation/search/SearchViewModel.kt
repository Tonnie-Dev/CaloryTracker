package com.uxstate.tracker_presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.core.domain.use_cases.FilterOutDigits
import com.uxstate.core.util.UIEvent
import com.uxstate.tracker_domain.use_cases.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val trackerUseCases: TrackerUseCases,
    private val filterOutDigits: FilterOutDigits
) : ViewModel() {


    //state
    var state by mutableStateOf(SearchState())
        private set

    //event
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SearchEvent) {

        when (event) {
            is SearchEvent.OnQueryChange -> {

                state = state.copy(query = event.query)
            }
            is SearchEvent.OnAmountForFoodChange -> {
                //  state = state.copy()

                //access the list from state to map it
                state = state.copy(trackableFoods = state.trackableFoods.map {

                    //compare event's food to TrackableFoods list items
                    if (event.food == it.food) {
//set the amount to match the event's food amount
                        it.copy(amount = filterOutDigits(event.amount))

                    }
                    //else the map iteration doesn't modify the food item amount
                    else it
                })

            }
            is SearchEvent.OnSearch -> {

                viewModelScope.launch {

                    trackerUseCases.searchFoodUseCase(query = state.query)
                }
            }
            is SearchEvent.OnToggleTrackableFood -> {
                //expand the menu

                //access the list of trackable foods to expand the current food item
                state = state.copy(trackableFoods = state.trackableFoods.map {

                    //compare the current food in iteration to the one in event

                    if (it.food == event.food) {

                        //modify it

                        it.copy(isExpanded = !it.isExpanded)
                    } else it
                })

            }
            is SearchEvent.OnTrackFoodClick -> {

                viewModelScope.launch {

                    trackerUseCases.trackFoodUseCase(
                        event.food,
                        mealType = event.mealType,
                        date = event.date,
                        amount = 0
                    )
                }
            }
            is SearchEvent.OnSearchFocusChange -> {


                //the hint should be shown when the TextField is not focussed and also empty
                state = state.copy(isHintVisible = !event.isFocused && state.query.isBlank())
            }
        }
    }

    fun trackFood(event: SearchEvent.OnTrackFoodClick) {


        viewModelScope.launch {

/*list.find {} returns the 1st element matching the predicate,
 or NULL if no such element was found*/

            //get a reference to the UI state
            val uiState = state.trackableFoods.find {

                it.food == event.food
            }
/*
            uiState?.let {
                trackerUseCases.trackFoodUseCase(
                    food = it.food,
                    amount = it.amount.toInt(),
                    mealType = event.mealType,
                    date = event.date
                )
            }*/


            trackerUseCases.trackFoodUseCase(
                food = uiState?.food ?: return@launch,
                amount = uiState.amount.toIntOrNull() ?: return@launch,
                mealType = event.mealType,
                date = event.date
            )

            //pop back stack and get back to the previous screen
_uiEvent.send(UIEvent.NavigateUp)

        }
    }
}