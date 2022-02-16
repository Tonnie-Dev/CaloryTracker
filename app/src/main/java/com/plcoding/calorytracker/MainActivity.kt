package com.plcoding.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plcoding.calorytracker.navigation.navigate
import com.plcoding.calorytracker.ui.theme.CaloryTrackerTheme
import com.uxstate.core.navigation.Route
import com.uxstate.core.util.UIEvent
import com.uxstate.onboarding_presentation.welcome.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerTheme {
                val navController = rememberNavController()
                 NavHost(
                    navController = navController,
                    startDestination = Route.WELCOME,
                    builder = {

                        composable(route = Route.WELCOME, content = {

                            WelcomeScreen(onNavigate = navController::navigate)
                        })

                        composable(route = Route.AGE, content = {
                            //WelcomeScreen(onNavigate = navController::navigate)
                        })
                        composable(route = Route.GENDER, content = {

                        })

                        composable(route = Route.GOAL, content = {

                        })

                        composable(route = Route.HEIGHT, content = {

                        })

                        composable(route = Route.NUTRIENT_GOAL, content = {

                        })
                        composable(route = Route.SEARCH, content = {

                        })
                        composable(route = Route.TRACKER_OVERVIEW, content = {

                        })

                        composable(route = Route.ACTIVITY, content = {

                        })
                    })
            }
        }
    }
}