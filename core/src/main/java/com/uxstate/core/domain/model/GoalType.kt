package com.uxstate.core.domain.model

sealed class GoalType(val goal:String){

/*the name arg helps us save entry on SharedPrefs as we
    cannot save sealed class in SharedPrefs*/
    object LoseWeight:GoalType("lose_weight")
    object GainWeight:GoalType("gain_weight")
    object KeepWeight:GoalType("keep_weight")


    //easily take a string and return a gender  object
    companion object{
        fun fromString(goal: String) :GoalType {

            return when (goal){

                "lose_weight" -> LoseWeight
                "gain_weight" -> GainWeight
                "keep_weight" -> KeepWeight
                else -> KeepWeight
            }
        }
    }
}
