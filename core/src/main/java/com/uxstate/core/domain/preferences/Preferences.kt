package com.uxstate.core.domain.preferences

import com.uxstate.core.domain.model.ActivityLevel
import com.uxstate.core.domain.model.Gender
import com.uxstate.core.domain.model.GoalType


//Defines what preferences can do - e.g. read & write age, height etc
interface Preferences {

    fun saveGender(gender: Gender)
    fun saveAge(age:Int)
    fun saveWeight(weight:Float)
    fun saveHeight(height:Int)
    fun saveActivityLevel(level:ActivityLevel)
    fun saveGoalType(goalType: GoalType)
    fun saveCarbRation(ratio:Float)
}