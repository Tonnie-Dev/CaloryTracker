package com.uxstate.tracker_presentation.tracker_overview

import com.uxstate.tracker_domain.model.TrackableFood
import com.uxstate.tracker_domain.model.TrackedFood
import java.time.LocalDate

sealed class TrackerOverViewEvent(){

    object OnNextDayClick:TrackerOverViewEvent()
    object OnPreviousDayClick:TrackerOverViewEvent()
   data class OnToggleMealClick(val meal: Meal):TrackerOverViewEvent()
    data class OnDeleteTrackedFoodClick(val food:TrackedFood):TrackerOverViewEvent()
    data class OnAddFoodScreen(val meal: Meal):TrackerOverViewEvent()
}
