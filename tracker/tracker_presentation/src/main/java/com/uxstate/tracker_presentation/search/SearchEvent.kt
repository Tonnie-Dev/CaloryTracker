package com.uxstate.tracker_presentation.search

import com.uxstate.tracker_domain.model.MealType
import com.uxstate.tracker_domain.model.TrackableFood
import java.time.LocalDate

sealed class SearchEvent {


    data class OnQueryChange(val query: String) : SearchEvent()
    object OnSearch : SearchEvent()
    data class OnToggleTrackableFood(val food: TrackableFood) : SearchEvent()
    data class OnAmountForFoodChange(val food: TrackableFood, val amount: String) : SearchEvent()
    data class OnTrackFoodClick(val food: TrackableFood, val mealType: MealType, val date:LocalDate):SearchEvent()
    //when we click on the search field to trigger hint hiding for basic TextField
    data class OnSearchFocusChange(val isFocused:Boolean):SearchEvent()
}
