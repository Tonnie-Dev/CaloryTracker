package com.uxstate.onboarding_presentation.weight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.core.domain.use_cases.FilterOutDigits
import com.uxstate.core.navigation.Route
import com.uxstate.core.util.UIEvent
import com.uxstate.core.util.UiText
import com.uxstate.onboarding_presentation.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
    private val prefs: Preferences
) :
    ViewModel() {

    var weight by mutableStateOf("80.0")
    private set

    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onEnterWeight(text: String) {

        if (text.length <= 5) {

            weight = text
        }
    }

    fun onNextClick() {

        viewModelScope.launch {


            //save weight


            val weightNumber = weight.toFloatOrNull() ?: kotlin.run {

                _uiEvent.send(
                    UIEvent.ShowSnackbar(
                        UiText.StringResource(
                            resId = R.string.error_weight_cant_be_empty
                        )
                    )
                )

                return@launch
            }

            prefs.saveWeight(weight = weightNumber)
            //navigate

            _uiEvent.send(UIEvent.Navigate(Route.ACTIVITY))

        }

    }

}