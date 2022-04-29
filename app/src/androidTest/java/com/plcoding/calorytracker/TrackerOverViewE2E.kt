package com.plcoding.calorytracker


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.common.truth.Truth.assertThat


import com.plcoding.calorytracker.navigation.Route
import com.plcoding.calorytracker.repository.TrackerRepositoryFake
import com.plcoding.calorytracker.ui.theme.CaloryTrackerTheme
import com.uxstate.core.domain.model.ActivityLevel
import com.uxstate.core.domain.model.Gender
import com.uxstate.core.domain.model.GoalType
import com.uxstate.core.domain.model.UserInfo
import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.core.domain.use_cases.FilterOutDigits
import com.uxstate.tracker_domain.model.TrackableFood
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
import kotlin.math.roundToInt

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
            CaloryTrackerTheme() {
    
                navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(modifier = Modifier.fillMaxSize(), scaffoldState = scaffoldState) {
                    NavHost(navController = navController,
                        startDestination = Route.TRACKER_OVERVIEW,
                        builder = {
                
                            //Screen 1
                            composable(route = Route.TRACKER_OVERVIEW, content = {
                    
                                TrackerOverviewScreen(onNavigateToSearch = { mealName, day, month, year ->
                                    navController.navigate(
                                        Route.SEARCH + "/$mealName" + "/$day" + "/$month" + "/$year"
                                    )
                                })
                            })
                
                            //Screen 2
                            composable(route = Route.SEARCH + "/{mealName}/{dayOfMonth}/{month}/{year}",
                                arguments = listOf(navArgument(name = "mealName") {
                                    type = NavType.StringType
                                },
                                    navArgument(name = "dayOfMonth") { type = NavType.IntType },
                                    navArgument(name = "month") { type = NavType.IntType },
                                    navArgument(name = "year") { type = NavType.IntType }),
                                content = {
                        
                                    val mealName = it.arguments?.getString("mealName")!!
                                    val dayOfMonth = it.arguments?.getInt("dayOfMonth")!!
                                    val month = it.arguments?.getInt("month")!!
                                    val year = it.arguments?.getInt("year")!!
                                    SearchScreen(scaffoldState = scaffoldState,
                                        mealName = mealName,
                                        dayOfMonth = dayOfMonth,
                                        month = month,
                                        year = year,
                                        onNavigateUp = { navController.navigateUp() })
                                })
                
                
                        })
                }
                
            }
            
          
           
            
        }
    }
    
    @Test
    fun addBreakfast_appearsUnderBreakfast_nutrientsProperlyCalculated() {
        
        //set the list of trackable food that the repo will respond with
        
        repository.searchResults = listOf(
            TrackableFood(
                name = "banana",
                carbsPer100g = 50,
                proteinPer100g = 5,
                fatsPer100g = 1,
                imageUrl = null,
                caloriesPer100g = 150
            )
        )
        
        //simulate user behaviour
        val addedAmount = 150
        
        //calculated expected nutrients
        val expectedCalories = (1.5f * 150).roundToInt()
        val expectedProteins = (1.5f * 5).toInt()
        val expectedFats = (1.5f * 1).toInt()
        val expectedCarbs = (1.5f * 50).toInt()
        
        //check that 'Add Button' is not shown/test button toggle
        composeRule
                .onNodeWithText("Add Breakfast")
                .assertDoesNotExist()
        
        //find breakfast section using breakfast image's content desc then click it
        composeRule
                .onNodeWithContentDescription("Breakfast")
                .performClick()/*
        //perform click
        composeRule
                .onNodeWithContentDescription("Breakfast")
                .performClick()*/
        
        //assert button is toggled
        composeRule
                .onNodeWithText("Add Breakfast")
                .assertIsDisplayed()
        
        //perform Add Button click
        composeRule
                .onNodeWithText("Add Breakfast")
                .performClick()
        
        //check navigation
        assertThat(
            navController.currentDestination?.route?.startsWith(Route.SEARCH)
        ).isTrue()
        
        //perform text input
        composeRule
                .onNodeWithTag("search_textfield") //search value doesn't matter as the repo responds with the same trackbleFoods
                .performTextInput("banana")
        
        //perform search icon click
        composeRule
                .onNodeWithContentDescription("Search...")
                .performClick()
        
        //find the any node with text and perform click
        
        composeRule
                .onNodeWithText("Carbs")
                .performClick()
        
        //add amount
        composeRule
                .onNodeWithContentDescription("Amount")
                .performTextInput(addedAmount.toString())
        
        //click save icon
        composeRule
                .onNodeWithContentDescription("track")
                .performClick()
        
        //check the current screen
        assertThat(navController.currentDestination?.route?.startsWith(Route.TRACKER_OVERVIEW)).isTrue()
        
        /* we have multiple nodes with our given protein or calories amount
        * to specify which node we mean we use onAllNodesWithText()*/
        
        composeRule
                .onAllNodesWithText(expectedCalories.toString())
                .onFirst()
                .assertIsDisplayed()
    
        composeRule
                .onAllNodesWithText(expectedCarbs.toString())
                .onFirst()
                .assertIsDisplayed()
    
        composeRule
                .onAllNodesWithText(expectedProteins.toString())
                .onFirst()
                .assertIsDisplayed()
        composeRule
                .onAllNodesWithText(expectedFats.toString())
                .onFirst()
                .assertIsDisplayed()
    }
    
    
}