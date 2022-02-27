package com.plcoding.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plcoding.calorytracker.navigation.navigate
import com.plcoding.calorytracker.ui.theme.CaloryTrackerTheme
import com.uxstate.core.navigation.Route
import com.uxstate.onboarding_presentation.activity.ActivityLevelScreen
import com.uxstate.onboarding_presentation.age.AgeScreen
import com.uxstate.onboarding_presentation.gender.GenderScreen
import com.uxstate.onboarding_presentation.goal.GoalScreen
import com.uxstate.onboarding_presentation.height.HeightScreen
import com.uxstate.onboarding_presentation.nutrients_goal.NutrientsScreen
import com.uxstate.onboarding_presentation.weight.WeightScreen
import com.uxstate.onboarding_presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                
                Scaffold(modifier = Modifier.fillMaxSize(), scaffoldState = scaffoldState) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.WELCOME,
                        builder = {

                            composable(route = Route.WELCOME, content = {

                                WelcomeScreen(onNavigate = navController::navigate)
                            })

                            composable(route = Route.GENDER, content = {

                                GenderScreen(onNavigate = navController::navigate)
                            })

                            composable(route = Route.AGE, content = {

                                AgeScreen(onNavigate = navController::navigate, scaffoldState = scaffoldState)

                            })
                            composable(route = Route.HEIGHT, content = {
                                
                                HeightScreen(scaffoldState = scaffoldState, onNavigate = navController::navigate)

                            })

                            composable(route = Route.WEIGHT, content = {
                                WeightScreen(scaffoldState = scaffoldState, onNavigate = navController::navigate)

                            })

                            composable(route = Route.ACTIVITY, content = {

                                ActivityLevelScreen( onNavigate = navController::navigate)
                            })
                            composable(route = Route.GOAL, content = {

                                GoalScreen(onNavigate = navController::navigate)
                            })



                            composable(route = Route.NUTRIENT_GOAL, content = {
                                
                                NutrientsScreen(
                                    onNavigate = navController::navigate,
                                    scaffoldState = scaffoldState
                                )

                            })
                            composable(route = Route.SEARCH, content = {

                            })
                            composable(route = Route.TRACKER_OVERVIEW, content = {

                            })


                        })
                }

            }
        }
    }
}