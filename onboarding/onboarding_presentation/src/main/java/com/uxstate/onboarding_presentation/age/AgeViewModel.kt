package com.uxstate.onboarding_presentation.age

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
class AgeViewModel @Inject constructor(private val prefs: Preferences, private val filterOutDigits: FilterOutDigits) :
    ViewModel() {


    var age by mutableStateOf("20")
        private set

   private val _uiEvent = Channel<UIEvent>()
    //public version
    val uiEvents = _uiEvent.receiveAsFlow()


    fun onClickNext(){

        viewModelScope.launch {

            /*use toIntOrNull in case the TextField is empty*/
            val ageNumber = age.toIntOrNull() ?: kotlin.run {

                _uiEvent.send(UIEvent.ShowSnackbar(UiText.StringResource(R.string.error_age_cant_be_empty)))


               /* @label syntax is used for specifying which
                 function among several nested ones this
                 statement returns from.*/

                //return control back to launch{} and loops in case age is still null
                return@launch
            }


            //by the time we get here age won't be null
            prefs.saveAge(ageNumber)

            _uiEvent.send(UIEvent.Navigate(route = Route.HEIGHT))
        }
    }

    fun onAgeEnter(newAgeValue:String){


      //keep age at 3 chars max
        if (newAgeValue.length <=3){
            /*filtering would be a business logic and is not allowed in
            * ViewModel in strict clean Architecture.
            *
            * For Business logic we need to create Use Cases
            age = newAgeValue.filter { a -> a.isDigit() }*/

            age = filterOutDigits(newAgeValue)

        }
    }
}