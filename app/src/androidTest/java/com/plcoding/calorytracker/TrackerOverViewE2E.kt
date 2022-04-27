package com.plcoding.calorytracker




import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.plcoding.calorytracker.repository.TrackerRepositoryFake
import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.tracker_domain.use_cases.TrackerUseCases
import com.uxstate.tracker_presentation.search.SearchViewModel
import com.uxstate.tracker_presentation.tracker_overview.TrackerOverviewViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule

@HiltAndroidTest
class TrackerOverViewE2E {
    
    //define hilt rule
    @get:Rule
    val hiltRule = HiltAndroidRule(this)
    
    private lateinit var repositoryFake: TrackerRepositoryFake
    private lateinit var useCases:TrackerUseCases
    private lateinit var prefs:Preferences
    
    //viewModels for our 2 screens
    
    private lateinit var trackerOverviewViewModel:TrackerOverviewViewModel
    private lateinit var searchViewModel:SearchViewModel
    
    //to verify nav worked
    private lateinit var navController:NavHostController
    
    @Before
    fun setUp(){
        //injects dependencies in this test class for dagger hilt
        hiltRule.inject()
    }
   
    
}