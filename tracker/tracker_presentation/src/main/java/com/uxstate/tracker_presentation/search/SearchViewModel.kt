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

    fun onEvent(event: SearchEvent){

        when(event){
            is SearchEvent.OnQueryChange -> {

                state = state.copy(query = event.query)
            }
            is SearchEvent.OnAmountForFoodChange -> {
              //  state = state.copy()

            }
            is SearchEvent.OnSearch -> {

               viewModelScope.launch {

                   trackerUseCases.searchFoodUseCase(query = state.query)
               }
            }
            is SearchEvent.OnToggleTrackableFood -> {


            }
            is SearchEvent.OnTrackFoodClick -> {

                viewModelScope.launch {

                    trackerUseCases.trackFoodUseCase(event.food, mealType = event.mealType, date = event.date, amount = 0)
                }
            }
            is SearchEvent.OnSearchFocusChange -> {}
        }
    }
}