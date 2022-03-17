package com.uxstate.tracker_presentation.tracker_overview.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.tracker_presentation.R
import com.uxstate.tracker_presentation.components.NutrientsInfo
import com.uxstate.tracker_presentation.components.UnitDisplay
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

                    Text(
                        text = meal.name.asString(context = context),
                        style = MaterialTheme.typography.h3
                    )

                    Icon(
                        imageVector = if (meal.isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = if (meal.isExpanded) stringResource(id = R.string.collapse) else stringResource(
                            id = R.string.extend
                        )
                    )
                }

                Spacer(modifier = Modifier.height(spacing.spaceSmall))


                //lower row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    UnitDisplay(
                        amount = meal.carbs,
                        unit = stringResource(id = R.string.kcal),
                        unitTextSize = 30.sp
                    )

                    Row() {

                        NutrientsInfo(
                            name = stringResource(id = R.string.carbs),
                            amount = meal.carbs,
                            unit = stringResource(
                                id = R.string.grams
                            )
                        )


                        NutrientsInfo(
                            name = stringResource(id = R.string.protein),
                            amount = meal.proteins,
                            unit = stringResource(
                                id = R.string.grams
                            )
                        )

                        NutrientsInfo(
                            name = stringResource(id = R.string.fat),
                            amount = meal.fats,
                            unit = stringResource(
                                id = R.string.grams
                            )
                        )
                    }
                }
            }
        }
    }
    
    Spacer(modifier = Modifier.height(spacing.spaceMedium))
    
    
    AnimatedVisibility(visible = meal.isExpanded) {

        //toggle the content that we passed
        content()
    }
}