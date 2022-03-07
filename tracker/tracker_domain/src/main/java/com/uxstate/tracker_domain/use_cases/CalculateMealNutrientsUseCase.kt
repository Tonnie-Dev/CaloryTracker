package com.uxstate.tracker_domain.use_cases

import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.tracker_domain.model.MealType

/*use SharedPreferences to retrieve values entered during onboarding*/
class CalculateMealNutrientsUseCase(private val prefs: Preferences) {

    //invoke() takes trackedFood from the db for a given day
    //invoke() calculates values for display on the UI separately
    //invoke() gives us a list of tracked food for a given day


    //stores nutrients for a specific meal type e.g. breakfast
    data class MealNutrients(
        val carbs: Int,
        val proteins: Int,
        val fats: Int,
        val calories: Int,
        val mealType: MealType
    )

    //the use case will return this data class
    data class Result(

        //goals for the day
        val carbsGoal: Int,
        val proteinsGoal: Int,
        val fatsGoal: Int,
        val caloriesGoal: Int,

        //total consumed for the day
        val totalCarbs: Int,
        val totalProteins: Int,
        val totalFats: Int,
        val totalCalories:Int,

        //map - you provide a mealType - e.g. breakfast and get an instance of the nutrients eaten
        val mealNutrients:Map<MealType, MealNutrients>
    )
}