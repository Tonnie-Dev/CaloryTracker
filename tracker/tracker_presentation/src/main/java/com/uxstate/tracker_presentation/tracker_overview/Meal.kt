package com.uxstate.tracker_presentation.tracker_overview

import androidx.annotation.DrawableRes
import com.uxstate.core.util.UiText
import com.uxstate.tracker_domain.model.MealType

/*the class is declared in the presentation layoyer and not in the domail layer
 This is because here we are dealing with UI state

 This is class is only used to reflect state for the UI and is not needed in
 the data or the domain layers*/
data class Meal(
    val name: UiText,
    @DrawableRes val drawableRes: Int,
    val mealType: MealType,
    val carbs: Int,
    val proteins: Int,
    val fates: Int,
    val isExpanded: Boolean
) {

}
