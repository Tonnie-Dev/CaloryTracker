package com.uxstate.onboarding_presentation.nutrients_goal


//container for fats, proteins & carbs states

//helps us not have multiple states on the ViewModel
data class NutrientsGoalState(
    val carbsRatio: String = "40",
    val proteinRatio: String = "30",
    val fatRatio: String = "30"
)
