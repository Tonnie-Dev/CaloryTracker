package com.uxstate.tracker_presentation.tracker_overview

import androidx.annotation.DrawableRes
import com.uxstate.core.util.UiText
import com.uxstate.tracker_domain.model.MealType
import com.uxstate.tracker_presentation.R

/*the class is declared in the presentation layoyer and not in the domail layer
 This is because here we are dealing with UI state

 This is class is only used to reflect state for the UI and is not needed in
 the data or the domain layers*/
data class Meal(
    val name: UiText,
    @DrawableRes val drawableRes: Int,
    val mealType: MealType,
    val carbs: Int = 0,
    val proteins: Int = 0,
    val fats: Int = 0,
    val calories: Int = 0,
    val isExpanded: Boolean = false
)

val defaultMeals = listOf(
    Meal(
        name = UiText.StringResource(R.string.breakfast),
        drawableRes = R.drawable.ic_breakfast,
        mealType = MealType.Breakfast,

        ),
    Meal(
        name = UiText.StringResource(R.string.lunch),
        drawableRes = R.drawable.ic_lunch,
        mealType = MealType.Lunch,

        ),
    Meal(
        name = UiText.StringResource(R.string.dinner),
        drawableRes = R.drawable.ic_dinner,
        mealType = MealType.Dinner,

        ),
    Meal(
        name = UiText.StringResource(R.string.snacks),
        drawableRes = R.drawable.ic_snack,
        mealType = MealType.Snack,

        ),
)