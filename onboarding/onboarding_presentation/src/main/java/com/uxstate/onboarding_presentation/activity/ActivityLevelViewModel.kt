package com.uxstate.onboarding_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.core.domain.model.ActivityLevel
import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.core.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityLevelViewModel @Inject constructor(private val prefs: Preferences) :
    ViewModel() {


    var activityLevel by mutableStateOf<ActivityLevel>(ActivityLevel.Medium)
        private set
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onSelectActivityLevel(selectedActivityLevel: ActivityLevel) {

        //update activity level
        activityLevel = selectedActivityLevel



    }


        fun onNextClick(){

                viewModelScope.launch {

                        //save level

                        prefs.saveActivityLevel(activityLevel)

                        //navigate
                       // _uiEvent.send(UIEvent.Successs(route = Route.GOAL))

                    /*apply Success navigation instead of Route which is the core module
           therefore making the onboarding module self-contained*/

                    _uiEvent.send(UIEvent.Successs)

                }


        }

}