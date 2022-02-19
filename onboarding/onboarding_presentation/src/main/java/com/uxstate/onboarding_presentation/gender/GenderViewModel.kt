package com.uxstate.onboarding_presentation.gender


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.core.domain.model.Gender
import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.core.navigation.Route
import com.uxstate.core.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(private val prefs: Preferences) :
    ViewModel() {

    // we set the default gender as male
    //we use <Gender> so that the value can accept both genders

    /* private set ensure we observe but can only change the value
     from within the ViewModel - we should never change the vw state
     from within our composables*/
    var selectedGender by mutableStateOf<Gender>(Gender.Male)
        private set

    /*State flows upwards to the ViewModel and Events come down*/

    /*Channel is used to send one-time events to the UI e.g.
    * Navigation, snackbar, confirm successful validation */

    //ViewModel version
    private val _uiEvent = Channel<UIEvent>()

    //public uiEvent version - triggered once for every event sent to the UI
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onGenderClick(gender: Gender) {

        selectedGender = gender
    }


    fun onNextClick(): Unit {

        /*sending an event into a channel is a suspending operation*/

        viewModelScope.launch {

            //save gender into prefs

            prefs.saveGender(selectedGender)


            //navigate
            _uiEvent.send(UIEvent.Navigate(route = Route.AGE))
        }


    }
}