package com.plcoding.calorytracker


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.plcoding.calorytracker.navigation.Route
import com.plcoding.calorytracker.repository.TrackerRepositoryFake
import com.uxstate.core.domain.model.ActivityLevel
import com.uxstate.core.domain.model.Gender
import com.uxstate.core.domain.model.GoalType
import com.uxstate.core.domain.model.UserInfo
import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.core.domain.use_cases.FilterOutDigits
import com.uxstate.onboarding_presentation.activity.ActivityLevelScreen
import com.uxstate.onboarding_presentation.age.AgeScreen
import com.uxstate.onboarding_presentation.gender.GenderScreen
import com.uxstate.onboarding_presentation.goal.GoalScreen
import com.uxstate.onboarding_presentation.height.HeightScreen
import com.uxstate.onboarding_presentation.nutrients_goal.NutrientsScreen
import com.uxstate.onboarding_presentation.weight.WeightScreen
import com.uxstate.onboarding_presentation.welcome.WelcomeScreen
import com.uxstate.tracker_domain.use_cases.*
import com.uxstate.tracker_presentation.search.SearchScreen
import com.uxstate.tracker_presentation.search.SearchViewModel
import com.uxstate.tracker_presentation.tracker_overview.TrackerOverviewScreen
import com.uxstate.tracker_presentation.tracker_overview.TrackerOverviewViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

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
        
        //initializing the 2 viewModels
        trackerOverviewViewModel =
            TrackerOverviewViewModel(trackerUseCases = useCases, prefs = prefs)
        searchViewModel =
            SearchViewModel(trackerUseCases = useCases, filterOutDigits = FilterOutDigits())
        
        /*set up the composables that we want to test i.e. don't incude onboarding
        * for every test case we will need to define the compose rule*/
        
        composeRule.setContent {
            
            val scaffoldState = rememberScaffoldState()
            Scaffold(modifier = Modifier.fillMaxSize(), scaffoldState = scaffoldState) {
                NavHost(
                    navController = navController,
                    startDestination =  Route.TRACKER_OVERVIEW,
                    builder = {
                
                       //Screen 1
                        composable(route = Route.TRACKER_OVERVIEW, content = {
                    
                            TrackerOverviewScreen(onNavigateToSearch = { mealName, day, month, year ->
                                navController.navigate(
                                    Route.SEARCH +
                                            "/$mealName"+
                                            "/$day" +
                                            "/$month"+
                                            "/$year"
                                )
                            })
                        })
                        
                        //Screen 2
                        composable(route = Route.SEARCH + "/{mealName}/{dayOfMonth}/{month}/{year}",
                            arguments = listOf(
                                navArgument(name = "mealName") { type = NavType.StringType },
                                navArgument(name = "dayOfMonth") { type = NavType.IntType },
                                navArgument(name = "month") { type = NavType.IntType },
                                navArgument(name = "year") { type = NavType.IntType }),
                            content = {
                        
                                val mealName = it.arguments?.getString("mealName")!!
                                val dayOfMonth = it.arguments?.getInt("dayOfMonth")!!
                                val month = it.arguments?.getInt("month")!!
                                val year = it.arguments?.getInt("year")!!
                                SearchScreen(
                                    scaffoldState = scaffoldState,
                                    mealName = mealName,
                                    dayOfMonth = dayOfMonth,
                                    month = month,
                                    year = year,
                                    onNavigateUp = { navController.navigateUp() }
                                )
                            })
                
                
                    })
            }
        
        }
    }
    @Test
    fun addBreakfast_appearsUnderBreakfast_nutrientsProperlyCalculated(){}
    
}