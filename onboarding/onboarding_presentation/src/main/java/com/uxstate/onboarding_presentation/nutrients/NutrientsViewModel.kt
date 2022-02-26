package com.uxstate.onboarding_presentation.nutrients

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.core.domain.use_cases.FilterOutDigits
import com.uxstate.core.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class NutrientsViewModel @Inject constructor(
    private val prefs: Preferences,
    private val filterOutDigits: FilterOutDigits
) :
    ViewModel() {


    var carbs by mutableStateOf("40")
        private set

    var proteins by mutableStateOf("30")
        private set

    var fats by mutableStateOf("30")
        private set


    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEnterCarbs(carbsValue: String) {

        carbs = filterOutDigits(carbsValue)
    }


    fun onEnterProteins(proteinsValue: String) {

        proteins = filterOutDigits(proteinsValue)
    }

    fun onEnterFats(fatsValue: String) {

        fats = fatsValue
    }
}