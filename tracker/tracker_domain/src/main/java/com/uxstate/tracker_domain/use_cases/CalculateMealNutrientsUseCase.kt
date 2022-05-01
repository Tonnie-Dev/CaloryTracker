package com.uxstate.tracker_domain.use_cases

import com.uxstate.core.domain.model.ActivityLevel
import com.uxstate.core.domain.model.Gender
import com.uxstate.core.domain.model.GoalType
import com.uxstate.core.domain.model.UserInfo
import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.tracker_domain.model.MealType
import com.uxstate.tracker_domain.model.TrackedFood
import kotlin.math.roundToInt

/*use SharedPreferences to retrieve values entered during onboarding*/
class CalculateMealNutrientsUseCase(private val prefs: Preferences) {
 
    //invoke() takes trackedFood list from the db for a given day
    //invoke() calculates values for display on the UI separately
    //invoke() gives us a list of tracked food for a given day

    operator fun invoke(trackedFoods: List<TrackedFood>): Result {

        //calculate nutrients and put them in a map
        val allNutrients = trackedFoods.groupBy {

            //group TrackedFood by lunch, breakfast dinner etc returni
            it.mealType


        }
              /*  map.mapValues is used to apply the transform function to each entry
               on this Map, in this case to convert the map to MealNutrients.*/
                .mapValues { entry ->

                    //Key in the map is the MealType i.e. Dinner, Lunch etc
                    val type = entry.key
                    
                    //values in the map
                    val foods = entry.value

                    //implicit return

                    //map to and return MealNutrients for a specific mealType
                    MealNutrients(
                        carbs = foods.sumOf { it.carbs },
                        proteins = foods.sumOf { it.protein },
                        fats = foods.sumOf { it.fat },
                        calories = foods.sumOf { it.calories },
                        mealType = type

                    )

                }

        val totalCarbs = allNutrients.values.sumOf {
            it.carbs
        }

        val totalProteins = allNutrients.values.sumOf {
            it.proteins
        }

        val totalFats = allNutrients.values.sumOf {
            it.fats

        }

        val totalCalories = allNutrients.values.sumOf {

            it.calories
        }


        //retrieve use info entered during onboarding

        val userInfo = prefs.loadUserInfo()


        val caloryGoal = dailyCaloryRequirement(userInfo)
        val carbsGoal = (caloryGoal * userInfo.carbRatio / 4f).roundToInt()
        val proteinGoal = (caloryGoal * userInfo.proteinRatio / 4f).roundToInt()
        val fatGoal = (caloryGoal * userInfo.fatRatio / 9f).roundToInt()


        return Result(
            //goals
            carbsGoal = carbsGoal,
            proteinsGoal = proteinGoal,
            fatsGoal = fatGoal,
            caloriesGoal = caloryGoal,

            //actual consumed
            totalCarbs = totalCarbs,
            totalProteins = totalProteins,
            totalFats = totalFats,
            totalCalories = totalCalories,

            //Map<MealType, MealNutrients> a map of MealNutrients to determine nutrients by MealType
            mealNutrients = allNutrients
        )
    }


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

        //total consumed for the day
        val totalCarbs: Int,
        val totalProteins: Int,
        val totalFats: Int,
        val totalCalories: Int,

        //goals for the day
        val carbsGoal: Int,
        val proteinsGoal: Int,
        val fatsGoal: Int,
        val caloriesGoal: Int,


        //map - you provide a mealType - e.g. breakfast and get an instance of the nutrients eaten
        val mealNutrients: Map<MealType, MealNutrients>
    )


    private fun bmr(userInfo: UserInfo): Int {
        return when(userInfo.gender) {
            is Gender.Male -> {
                (66.47f + 13.75f * userInfo.weight +
                        5f * userInfo.height - 6.75f * userInfo.age).roundToInt()
            }
            is Gender.Female ->  {
                (665.09f + 9.56f * userInfo.weight +
                        1.84f * userInfo.height - 4.67 * userInfo.age).roundToInt()
            }
    
           
        }
    }

    private fun dailyCaloryRequirement(userInfo: UserInfo): Int {
        val activityFactor = when(userInfo.activityLevel) {
            is ActivityLevel.Low -> 1.2f
            is ActivityLevel.Medium -> 1.3f
            is ActivityLevel.High -> 1.4f
    
            
        }
        val caloryExtra = when(userInfo.goalType) {
            is GoalType.LoseWeight -> -500
            is GoalType.KeepWeight -> 0
            is GoalType.GainWeight -> 500
        }
        return (bmr(userInfo) * activityFactor + caloryExtra).roundToInt()
    }
}