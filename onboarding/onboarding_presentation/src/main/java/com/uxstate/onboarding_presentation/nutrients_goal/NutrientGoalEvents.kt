package com.uxstate.onboarding_presentation.nutrients_goal

sealed class NutrientGoalEvents {

    data class OnCarbsRatioEnter(val ratio:String):NutrientGoalEvents()
    data class OnProteinsRatioEnter(val ratio: String):NutrientGoalEvents()
    data class OnFatsRatioEnter(val ratio: String):NutrientGoalEvents()
    object OnNextClick: NutrientGoalEvents()
}