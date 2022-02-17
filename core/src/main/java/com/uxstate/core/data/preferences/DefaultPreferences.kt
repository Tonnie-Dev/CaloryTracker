package com.uxstate.core.data.preferences

import com.uxstate.core.domain.model.ActivityLevel
import com.uxstate.core.domain.model.Gender
import com.uxstate.core.domain.model.GoalType
import com.uxstate.core.domain.model.UserInfo
import com.uxstate.core.domain.preferences.Preferences

class DefaultPreferences: Preferences {
    override fun saveGender(gender: Gender) {
        TODO("Not yet implemented")
    }

    override fun saveAge(age: Int) {
        TODO("Not yet implemented")
    }

    override fun saveWeight(weight: Float) {
        TODO("Not yet implemented")
    }

    override fun saveHeight(height: Int) {
        TODO("Not yet implemented")
    }

    override fun saveActivityLevel(level: ActivityLevel) {
        TODO("Not yet implemented")
    }

    override fun saveGoalType(goalType: GoalType) {
        TODO("Not yet implemented")
    }

    override fun saveCarbRation(ratio: Float) {
        TODO("Not yet implemented")
    }

    override fun saveProteinRatio(ratio: Float) {
        TODO("Not yet implemented")
    }

    override fun saveFatRatio(ratio: Float) {
        TODO("Not yet implemented")
    }

    override fun loadUserInfo(): UserInfo {
        TODO("Not yet implemented")
    }
}