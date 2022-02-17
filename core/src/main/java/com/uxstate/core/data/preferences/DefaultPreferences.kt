package com.uxstate.core.data.preferences

import android.content.SharedPreferences
import com.uxstate.core.domain.model.ActivityLevel
import com.uxstate.core.domain.model.Gender
import com.uxstate.core.domain.model.GoalType
import com.uxstate.core.domain.model.UserInfo
import com.uxstate.core.domain.preferences.Preferences

class DefaultPreferences(private val sharedPrefs: SharedPreferences) : Preferences {
    override fun saveGender(gender: Gender) {

        sharedPrefs.edit()
                .putString("gender", gender.name)
                .apply()

    }

    override fun saveAge(age: Int) {

        sharedPrefs.edit()
                .putInt("age", age)
                .apply()
    }

    override fun saveWeight(weight: Float) {

        sharedPrefs.edit().putFloat("weight", weight).apply()
    }

    override fun saveHeight(height: Int) {
        sharedPrefs.edit().putInt("height", height).apply()
    }

    override fun saveActivityLevel(level: ActivityLevel) {

        sharedPrefs.edit().putString("level", level.level ).apply()
    }

    override fun saveGoalType(goalType: GoalType) {

        sharedPrefs.edit().putString("goal",goalType.goal).apply()
    }

    override fun saveCarbRation(ratio: Float) {
     sharedPrefs.edit().putFloat("carbRatio", ratio).apply()
    }

    override fun saveProteinRatio(ratio: Float) {

        sharedPrefs.edit().putFloat("proteinRatio", ratio).apply()
    }

    override fun saveFatRatio(ratio: Float) {
      sharedPrefs.edit().putFloat("fatRatio", ratio ).apply()
    }

    override fun loadUserInfo(): UserInfo {
     return UserInfo(
         gender =sharedPrefs.getString("gender").fr,
         age = 0,
         weight = 0.0f,
         height = 0,
         activityLevel =,
         goalType =,
         carbRatio = 0.0f,
         proteinRatio = 0.0f,
         fatRatio = 0.0f
     )
    }
}