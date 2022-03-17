package com.uxstate.tracker_presentation.tracker_overview.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.tracker_presentation.tracker_overview.Meal

@Composable
fun ExpandableMeal(
    meal: Meal,
    onToggleClick: () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current
    Column(modifier = Modifier) {


        Row(
            modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onToggleClick() }
                    .padding(spacing.spaceMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {

//Image only occupies the space it only needs
            Image(
                painter = painterResource(id = meal.drawableRes),
                contentDescription = meal.name.asString(context = context)
            )
            //Set with modifier.weight(1f), the column will occupy the entire remaining space
            Column(modifier = Modifier.weight(1f)) {


                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Text(text = meal.mealType.name, style = MaterialTheme.typography.h3)
                }
            }
        }
    }
}