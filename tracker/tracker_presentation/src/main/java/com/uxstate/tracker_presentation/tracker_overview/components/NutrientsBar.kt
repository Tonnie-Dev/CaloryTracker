package com.uxstate.tracker_presentation.tracker_overview.components

import androidx.compose.animation.core.Animatable
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun NutrientsBar(
    carbs: Int,
    proteins: Int,
    fats: Int,
    calories: Int,
    caloriesGoal: Int,
    modifier: Modifier = Modifier
) {


    val background = MaterialTheme.colors.background

   /*color to denote when calories amount is exceeded - highlight the
 whole bar in dark red*/

    val caloriesExceeded = MaterialTheme.colors.error

    /*states to calculate the bar space occupied by nutrients*/
    val carbsWidthRatio = remember{ Animatable(0f)}
    val proteinsWidthRatio = remember{ Animatable(0f)}
    val fatsWidthRatio = remember{ Animatable(0f)}

    //animate the above values as soon as this composable is recomposed


    LaunchedEffect(
        key1 = carbsWidthRatio,
        key2 = proteinsWidthRatio,
        key3 = fatsWidthRatio,
        block ={}
    )

}