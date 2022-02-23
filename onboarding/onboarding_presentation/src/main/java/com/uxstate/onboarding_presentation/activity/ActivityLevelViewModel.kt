package com.uxstate.onboarding_presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.uxstate.core.domain.model.ActivityLevel
import com.uxstate.core.domain.model.Gender
import com.uxstate.core.domain.preferences.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActivityLevelViewModel @Inject constructor(private val prefs:Preferences) :
        ViewModel() {



                var activityLevel = mutableStateOf(ActivityLevel.Medium)
        
}