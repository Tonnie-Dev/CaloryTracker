package com.plcoding.calorytracker.navigation

import androidx.navigation.NavController
import com.uxstate.core.util.UIEvent

fun NavController.navigate(event:UIEvent.Navigate) {


    this.navigate(event.route)
}