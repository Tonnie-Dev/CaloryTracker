package com.uxstate.tracker_presentation.search

import com.uxstate.tracker_domain.model.TrackableFood


/*customized to hold fields or the search screenUI food state
instead of using the domain TrackableFood class.

This ensures we don't pollute the domain model with this
 2 different versions*/

data class TrackableFoodUiState(
    val food: TrackableFood,
    val isExpanded: Boolean = false,
    val amount: String = ""
)
