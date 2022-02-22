package com.uxstate.onboarding_presentation.height

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.core.domain.use_cases.FilterOutDigits
import com.uxstate.core.util.UIEvent
import com.uxstate.core.util.UiText
import com.uxstate.onboarding_presentation.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(
    private val prefs: Preferences,
    private val filterOutDigits: FilterOutDigits
) :
    ViewModel() {


    var height by mutableStateOf("180")
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEnterHeight(text: String) {

        viewModelScope.launch {


            val heightNumber = text.toIntOrNull() ?: kotlin.run {

                _uiEvent.send(UIEvent.ShowSnackbar(UiText.StringResource(R.string.whats_your_height)))

                return@launch
            }

            height = filterOutDigits(heightNumber.toString())
        }


    }

    

}