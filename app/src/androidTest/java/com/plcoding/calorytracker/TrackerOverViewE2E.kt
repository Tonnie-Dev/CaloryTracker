package com.plcoding.calorytracker


import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavHostController
import com.plcoding.calorytracker.repository.TrackerRepositoryFake
import com.uxstate.core.domain.model.ActivityLevel
import com.uxstate.core.domain.model.Gender
import com.uxstate.core.domain.model.GoalType
import com.uxstate.core.domain.model.UserInfo
import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.tracker_domain.use_cases.*
import com.uxstate.tracker_presentation.search.SearchViewModel
import com.uxstate.tracker_presentation.tracker_overview.TrackerOverviewViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule

@HiltAndroidTest
class TrackerOverViewE2E {
    
    //define hilt rule
    @get:Rule
    val hiltRule = HiltAndroidRule(this)
    
    //define JUnit rule for using jetpack compose specific stuff
    
    /*Specify the activity class that we want to use
     for the UI Test*/
    
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    
    //define dependencies
    private lateinit var repository: TrackerRepositoryFake
    private lateinit var useCases: TrackerUseCases
    private lateinit var prefs: Preferences
    
    //viewModels for our 2 screens
    
    private lateinit var trackerOverviewViewModel: TrackerOverviewViewModel
    private lateinit var searchViewModel: SearchViewModel
    
    //to verify nav worked
    private lateinit var navController: NavHostController
    
    @Before
    fun setUp() { //create mockk to initialize preferences
        
        prefs = mockk(relaxed = true) //make prefs.loadUserInfo return a custom UserInfo
        every { prefs.loadUserInfo() } returns UserInfo(
            gender = Gender.Male,
            age = 35,
            weight = 78.0f,
            height = 163,
            activityLevel = ActivityLevel.Medium,
            goalType = GoalType.LoseWeight,
            carbRatio = 0.6f,
            proteinRatio = 0.2f,
            fatRatio = 0.2f
        )
        
        //initialize repository
        repository = TrackerRepositoryFake()
        
        //initialize use cases
        
        useCases = TrackerUseCases(
            trackFoodUseCase = TrackFoodUseCase(repository),
            calculateMealNutrientsUseCase = CalculateMealNutrientsUseCase(prefs),
            deleteFoodUseCase = DeleteFoodUseCase(repository),
            getFoodsForDateUseCase = GetFoodsForDateUseCase(repository),
            searchFoodUseCase = SearchFoodUseCase(repository)
        )
    }
    
    
}