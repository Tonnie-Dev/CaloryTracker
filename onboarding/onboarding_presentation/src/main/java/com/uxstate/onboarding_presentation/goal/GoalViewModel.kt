package com.uxstate.onboarding_presentation.goal

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.prefs.Preferences
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(private val prefs: Preferences) :
        ViewModel() {
}