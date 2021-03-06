package com.uxstate.tracker_presentation.tracker_overview.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.uxstate.tracker_presentation.R
import com.uxstate.tracker_presentation.components.UnitDisplay

@Composable
fun NutrientsBarInfo(
    value: Int,
    goal: Int,
    name: String,
    color: Color,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 8.dp
) {


    val background = MaterialTheme.colors.background
    val goalExceededColor = MaterialTheme.colors.error
    val angelRatio = remember { Animatable(0f) }

    //use value to animate bar when value changes
    LaunchedEffect(key1 = value, block = {

        //animate when value has something
        angelRatio.animateTo(
            targetValue = if (goal > 0) value / goal.toFloat() else 0f,
            animationSpec = tween(durationMillis = 300)
        )

    })

    Box(modifier = modifier, contentAlignment = Alignment.Center) {

        Canvas(

            //make canvas square with the aspect ratio (width = height)
            modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),


            onDraw = {
                //BACKGROUND_ARC
                //background = white - set on the arc when goal is not exceeded
                drawArc(
                    color = if (value <= goal) background else goalExceededColor,
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    size = size,
                    style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
                )

                //FOREGROUND_ARC

                //drawn if value less than or equal to the goal
                if (value <= goal) {

                    drawArc(
                        color = color,
                        startAngle = 90f,
                        sweepAngle = angelRatio.value * 360f,
                        useCenter = false,
                        size = size,
                        style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
                    )
                }
            }


        )


        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UnitDisplay(
                amount = value,
                unit = stringResource(id = R.string.grams),
                amountColor = if (value <= goal) MaterialTheme.colors.onPrimary else goalExceededColor,
                unitColor = if (value <= goal) MaterialTheme.colors.onPrimary else goalExceededColor
            )
            Text(
                text = name,
                fontWeight = FontWeight.Light,
                color = if (value <= goal) MaterialTheme.colors.onPrimary else goalExceededColor,
                style = MaterialTheme.typography.body1
            )
        }
    }
}