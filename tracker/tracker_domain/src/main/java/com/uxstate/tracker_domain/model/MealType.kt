package com.uxstate.tracker_domain.model

//takes a string to easily parse the sub-classes to string
sealed class MealType(name: String) {

    object Breakfast : MealType(name = "breakfast")
    object Lunch : MealType(name = "lunch")
    object Dinner : MealType(name = "dinner")
    object Snack : MealType(name = "snack")


    companion object {

//construct a MealType object from a String
        fun fromString(mealType: String): MealType {

            return when (mealType) {
                "breakfast" -> Breakfast
                "lunch" -> Lunch
                "snack" -> Snack
                "dinner" -> Dinner

                else -> Breakfast
            }
        }
    }
}
