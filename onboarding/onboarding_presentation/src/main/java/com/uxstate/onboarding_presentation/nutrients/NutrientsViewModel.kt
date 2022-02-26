package com.uxstate.onboarding_presentation.nutrients

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.uxstate.core.domain.preferences.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NutrientsViewModel @Inject constructor(private val prefs:Preferences) :
        ViewModel() {


            val carbs by mutableStateOf("40")
            val proteins by mutableStateOf("30")
            val fats by mutableStateOf("30")
}