package com.uxstate.tracker_domain.use_cases

import com.uxstate.core.domain.model.ActivityLevel
import com.uxstate.core.domain.model.Gender
import com.uxstate.core.domain.model.GoalType
import com.uxstate.core.domain.model.UserInfo
import com.uxstate.core.domain.preferences.Preferences
import io.mockk.every
import io.mockk.mockk
import org.junit.Before


class CalculateMealNutrientsUseCaseTest {
    
    private lateinit var calculateMealNutrientsUseCase: CalculateMealNutrientsUseCase
    
    
    @Before
    fun setUp() {
        
        // use mockk<> () method to specify object to be mocked
        val prefs = mockk<Preferences>(relaxed = true)
    
        // use every to define what behaviour is going to be mocked
        every { prefs.loadUserInfo() } returns (UserInfo(
            gender = Gender.Male,
            age = 28,weight = 80f,
            height = 180,
            activityLevel = ActivityLevel.Medium,
            goalType = GoalType.KeepWeight,
            carbRatio = 0.4f,
            proteinRatio = 0.4f,
            fatRatio = 0.3f))
        
        //we pass in the mocked prefs instead of the real one
        calculateMealNutrientsUseCase = CalculateMealNutrientsUseCase(prefs)
    }
}