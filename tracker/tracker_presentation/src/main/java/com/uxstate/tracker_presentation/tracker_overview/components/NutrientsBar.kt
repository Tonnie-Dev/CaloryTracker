package com.uxstate.tracker_presentation.tracker_overview.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import com.uxstate.core_ui.CarbColor
import com.uxstate.core_ui.LocalSpacing

@Composable
fun NutrientsBar(
    carbs: Int,
    proteins: Int,
    fats: Int,
    calories: Int,
    caloriesGoal: Int,
    modifier: Modifier = Modifier
) {

    val spacing = LocalSpacing.current
    val background = MaterialTheme.colors.background

    /*color to denote when calories amount is exceeded - highlight the
  whole bar in dark red*/

    val caloriesExceeded = MaterialTheme.colors.error

    /*states to calculate the bar space occupied by nutrients*/
    val carbsWidthRatio = remember { Animatable(0f) }
    val proteinsWidthRatio = remember { Animatable(0f) }
    val fatsWidthRatio = remember { Animatable(0f) }

    //animate the above values as soon as this composable is recomposed

/*re-trigger this launch block as soon as carbsWidthRatio state changes
* which in return triggers the animation*/
    LaunchedEffect(
        key1 = carbsWidthRatio,
        block = {

            //animate float (1g of carbs = 4 calories
            carbsWidthRatio.animateTo(targetValue = (carbs * 4f) / caloriesGoal)

        }
    )


    LaunchedEffect(
        key1 = proteinsWidthRatio,
        block = {

            //animate float (1g of proteins = 4 calories
            proteinsWidthRatio.animateTo(targetValue = (proteins * 4f) / caloriesGoal)

        }
    )

    LaunchedEffect(
        key1 = fatsWidthRatio,
        block = {

            //animate float (1g of fats = 8 calories
            fatsWidthRatio.animateTo(targetValue = (fats * 9f) / caloriesGoal)

        }
    )


    //Canvas is used to draw custom shapes in the most simplest way
    Canvas(modifier = modifier, onDraw = {

        /*check if caloriesGoal was exceeded - fill the bar red
         else draw different colors for carbs, fats etc*/


        if (calories <= caloriesGoal) {

            //carbs width in px - size.width is provided by canvas's drawScope
            val carbsWidth = carbsWidthRatio.value * size.width

            //protein width in px - size.width is provided by canvas's drawScope
            val proteinsWidth = (proteinsWidthRatio.value * size.width)

            //fats width in px - size.width is provided by canvas's drawScope

            val fatsWidth = fatsWidthRatio.value * size.width


            //draw bar background
            drawRoundRect(color = background, size = size, cornerRadius = CornerRadius(100f))

            //draw carbs graph
            drawRoundRect(color = CarbColor, size = size, cornerRadius = CornerRadius(100f))
        }


    })
}