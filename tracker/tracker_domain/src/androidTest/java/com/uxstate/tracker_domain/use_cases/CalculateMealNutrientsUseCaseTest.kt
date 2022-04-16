package com.uxstate.tracker_domain.use_cases

import com.google.common.truth.Truth.assertThat
import com.uxstate.core.domain.model.ActivityLevel
import com.uxstate.core.domain.model.Gender
import com.uxstate.core.domain.model.GoalType
import com.uxstate.core.domain.model.UserInfo
import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.tracker_domain.model.MealType
import com.uxstate.tracker_domain.model.TrackedFood
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import kotlin.random.Random


//unit test for testing CalculateMealNutrientsUseCase
class CalculateMealNutrientsUseCaseTest {
    
    //use case to test
    private lateinit var calculateMealNutrientsUseCase: CalculateMealNutrientsUseCase
    
    
    @Before
    fun setUp() {
        
        // use mockk<> () method to specify object (dependency) to be mocked
        
        //it is relaxed to make  functions inside Prefs object return empty responses
        val prefs = mockk<Preferences>(relaxed = true)
        
        // use every block to define what behaviour, fxn to be mocked
        every { prefs.loadUserInfo() } returns (UserInfo(
            gender = Gender.Male,
            age = 28,
            weight = 80f,
            height = 180,
            activityLevel = ActivityLevel.Medium,
            goalType = GoalType.KeepWeight,
            carbRatio = 0.4f,
            proteinRatio = 0.3f,
            fatRatio = 0.3f
        ))/*To initialize the use case we need the mocked Prefs Object(Dependency)
        * instead of the real or creating a fake Prefs dependency*/
        
        calculateMealNutrientsUseCase = CalculateMealNutrientsUseCase(prefs)
    }
    
    @Test
    fun caloriesForBreakfastProperlyCalculated() {
        
        //create some random 30 trackedFood objects
        val trackedFoods = (1..30).map {
            
            TrackedFood(
                name = "name",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                imageUrl = null,
                mealType = MealType.fromString(
                    listOf(
                        "breakfast", "lunch", "dinner", "snack"
                    ).random()
                ),
                amount = 100,
                date = LocalDate.now(),
                calories = Random.nextInt(2000),
                id = null
            )
        }
        
        // invoke the useCase to produce a result which is wrapped in a Result data class
        val result = calculateMealNutrientsUseCase(trackedFoods)
        
        
        //use the result to find out how many calories we have for breakfast
        
        //mealNutrients: Map<MealType, MealNutrients>
        val breakfastCalories = result.mealNutrients.values //filter by breakfast
            .filter {
                it.mealType is MealType.Breakfast
            } //get calories sum for breakfast category
            .sumOf { it.calories }
        
        val expectedCalories =
            trackedFoods.filter { it.mealType is MealType.Breakfast }.sumOf { it.calories }
        
        //assertion - test subject put into brackets
        assertThat(breakfastCalories).isEqualTo(expectedCalories)
    }
    
    
    @Test
    fun dinnerCaloriesProperlyCalculated() {
        
        //generated random trackedFood items
        val trackedFoods = (1..30).map {
            
            TrackedFood(
                name = "food",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                imageUrl = null,
                mealType = MealType.fromString(
                    listOf(
                        "breakfast", "lunch", "dinner", "snack"
                    ).random()
                ),
                amount = 100,
                date = LocalDate.now(),
                calories = Random.nextInt(2000),
                id = null
            )
            
        }
        
        val result = calculateMealNutrientsUseCase(trackedFoods)
        
        //Map<MealType, MealNutrients>, get map's values, filter by dinner
        val dinnerCalories = result.mealNutrients.values.filter { it.mealType is MealType.Dinner }
            .sumOf { it.calories }
        
        
        val expectedCalories =
            trackedFoods.filter { it.mealType is MealType.Dinner }.sumOf { it.calories }
        
        assertThat(dinnerCalories).isEqualTo(expectedCalories)
    }
    
    
    @Test
    fun snackProteinsProperlyCalculated() {
        
        
        //create random trackedFood items
        val trackedFoods = (1..30).map {
            TrackedFood(
                name = "chocolate",
                carbs = Random.nextInt(50),
                protein = Random.nextInt(50),
                fat = Random.nextInt(50),
                imageUrl = null,
                mealType = MealType.fromString(
                    listOf<String>(
                        "snack", "breakfast", "dinner", "lunch"
                    ).random()
                ),
                amount = 50,
                date = LocalDate.now(),
                calories = Random.nextInt(500),
                id = null
            )
        }
        
        //pass a list of trackedFoods to use case
        
        val result = calculateMealNutrientsUseCase(trackedFoods)
        
        
        //get the actual proteins total from result
        
        val snacksProteins = result.mealNutrients.values.filter { it.mealType is MealType.Snack }
            .sumOf { it.proteins }
        
        //calculate total proteins from consumed snacks under the random tracked foods
        val expectedProteins =
            trackedFoods.filter { it.mealType is MealType.Snack }.sumOf { it.protein }
        
        assertThat(snacksProteins).isEqualTo(expectedProteins)
        
    }
}