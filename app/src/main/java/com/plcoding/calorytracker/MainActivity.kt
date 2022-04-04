package com.plcoding.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState

import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.plcoding.calorytracker.navigation.navigate
import com.plcoding.calorytracker.ui.theme.CaloryTrackerTheme
import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.core.navigation.Route
import com.uxstate.onboarding_presentation.activity.ActivityLevelScreen
import com.uxstate.onboarding_presentation.age.AgeScreen
import com.uxstate.onboarding_presentation.gender.GenderScreen
import com.uxstate.onboarding_presentation.goal.GoalScreen
import com.uxstate.onboarding_presentation.height.HeightScreen
import com.uxstate.onboarding_presentation.nutrients_goal.NutrientsScreen
import com.uxstate.onboarding_presentation.weight.WeightScreen
import com.uxstate.onboarding_presentation.welcome.WelcomeScreen
import com.uxstate.tracker_presentation.search.SearchScreen
import com.uxstate.tracker_presentation.tracker_overview.TrackerOverviewScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//@ExperimentalComposeUiApi
//@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val shouldShowOnboarding = preferences.loadShouldShowOnboarding()


        setContent {


            CaloryTrackerTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()

                Scaffold(modifier = Modifier.fillMaxSize(), scaffoldState = scaffoldState) {
                    NavHost(
                        navController = navController,
                        startDestination = if (shouldShowOnboarding)  Route.WELCOME else Route.TRACKER_OVERVIEW,
                        builder = {

                            composable(route =Route.WELCOME , content = {

                                WelcomeScreen(onNextClick = navController::navigate)
                            })

                            composable(route = Route.GENDER, content = {

                                GenderScreen(onNavigate = navController::navigate)
                            })

                            composable(route = Route.AGE, content = {

                                AgeScreen(
                                    onNextClick = navController::navigate,
                                    scaffoldState = scaffoldState
                                )

                            })
                            composable(route = Route.HEIGHT, content = {

                                HeightScreen(
                                    scaffoldState = scaffoldState,
                                    onNextClick = navController::navigate
                                )

                            })

                            composable(route = Route.WEIGHT, content = {
                                WeightScreen(
                                    scaffoldState = scaffoldState,
                                    onNavigate = navController::navigate
                                )

                            })

                            composable(route = Route.ACTIVITY, content = {

                                ActivityLevelScreen(onNextClick = navController::navigate)
                            })
                            composable(route = Route.GOAL, content = {

                                GoalScreen(onNextClick = navController::navigate)
                            })



                            composable(route = Route.NUTRIENT_GOAL, content = {

                                NutrientsScreen(
                                    onNavigate = navController::navigate,
                                    scaffoldState = scaffoldState
                                )

                            })

                            composable(route = Route.TRACKER_OVERVIEW, content = {

                                TrackerOverviewScreen(onNavigate = navController::navigate)
                            })
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
    }
}


