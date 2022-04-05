package com.uxstate.core.util

//once once events e.g. popping the backstack, snackbar, navigation
sealed class UIEvent {


   object Successs:UIEvent()
    object NavigateUp:UIEvent()
    data class ShowSnackbar(val message:UiText):UIEvent()
}