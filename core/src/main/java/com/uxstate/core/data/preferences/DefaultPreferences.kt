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
                .putString(Preferences.KEY_GENDER, gender.name)
                .apply()

    }

    override fun saveAge(age: Int) {

        sharedPrefs.edit()
                .putInt(Preferences.KEY_AGE, age)
                .apply()
    }

    override fun saveWeight(weight: Float) {

        sharedPrefs.edit()
                .putFloat(Preferences.KEY_WEIGHT, weight)
                .apply()
    }

    override fun saveHeight(height: Int) {
        sharedPrefs.edit()
                .putInt(Preferences.KEY_HEIGHT, height)
                .apply()
    }

    override fun saveActivityLevel(level: ActivityLevel) {

        sharedPrefs.edit()
                .putString(Preferences.KEY_ACTIVITY_LEVEL, level.level)
                .apply()
    }

    override fun saveGoalType(goalType: GoalType) {

        sharedPrefs.edit()
                .putString(Preferences.KEY_GOAL_TYPE, goalType.goal)
                .apply()
    }


    override fun saveCarbRatio(ratio: Float) {
        sharedPrefs.edit()
                .putFloat(Preferences.KEY_CARB_RATIO, ratio)
                .apply()
    }

    override fun saveProteinRatio(ratio: Float) {

        sharedPrefs.edit()
                .putFloat(Preferences.KEY_PROTEIN_RATIO, ratio)
                .apply()
    }

    override fun saveFatRatio(ratio: Float) {
        sharedPrefs.edit()
                .putFloat(Preferences.KEY_PROTEIN_RATIO, ratio)
                .apply()
    }

    override fun loadUserInfo(): UserInfo {

        val genderString = sharedPrefs.getString(Preferences.KEY_GENDER, null)
        val activityLevelString = sharedPrefs.getString(Preferences.KEY_ACTIVITY_LEVEL, null)
        val goalTypeString = sharedPrefs.getString(Preferences.KEY_GOAL_TYPE, null)
        val age = sharedPrefs.getInt(Preferences.KEY_AGE, -1)
        val weight = sharedPrefs.getFloat(Preferences.KEY_WEIGHT, -1f)
        val height = sharedPrefs.getInt(Preferences.KEY_HEIGHT, -1)
        val carbRatio = sharedPrefs.getFloat(Preferences.KEY_CARB_RATIO, -1f)
        val proteinRatio = sharedPrefs.getFloat(Preferences.KEY_PROTEIN_RATIO, -1f)
        val fatRatio = sharedPrefs.getFloat(Preferences.KEY_FAT_RATIO, -1f)



        return UserInfo(


            gender = Gender.fromString(genderString ?: "male"),
            age = age,
            weight = weight,
            height = height,
            activityLevel = ActivityLevel.fromString(activityLevelString ?: "medium"),
            goalType = GoalType.fromString(goalTypeString ?: "keep_weight"),
            carbRatio = carbRatio,
            proteinRatio = proteinRatio,
            fatRatio = fatRatio
        )
    }

    override fun saveShouldShowOnboarding(shouldShow: Boolean) {
        sharedPrefs.edit()
                .putBoolean(Preferences.KEY_SHOW_ONBOARDING, shouldShow)
                .apply()
    }

    override fun loadShouldShowOnboarding(): Boolean {
       return sharedPrefs.getBoolean(Preferences.KEY_SHOW_ONBOARDING,false)
           }
}