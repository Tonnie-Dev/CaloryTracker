package com.uxstate.onboarding_presentation.age

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.core.navigation.Route
import com.uxstate.core.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(private val prefs: Preferences) :
    ViewModel() {


    var age = mutableStateOf(0)
        private set

   private val _uiEvent = Channel<UIEvent>()
    //public version
    val uiEvents = _uiEvent.receiveAsFlow()


    fun onClickNext(){

        viewModelScope.launch {

            prefs.saveAge(age.value)

            _uiEvent.send(UIEvent.Navigate(route = Route.ACTIVITY))
        }
    }

    fun onA
}