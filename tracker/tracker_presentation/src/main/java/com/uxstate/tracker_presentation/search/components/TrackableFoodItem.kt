package com.uxstate.tracker_presentation.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.tracker_presentation.R
import com.uxstate.tracker_presentation.components.UnitDisplay
import com.uxstate.tracker_presentation.search.TrackableFoodUiState
import androidx.compose.foundation.layout.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.uxstate.tracker_presentation.components.NutrientsInfo

@OptIn(ExperimentalCoilApi::class)
@Composable
fun TrackableFoodItem(

    trackableFoodUiState: TrackableFoodUiState,
    onClick: () -> Unit,
    onAmountChange: (String) -> Unit,
    onTrack: () -> Unit, modifier: Modifier = Modifier
) {

    val food = trackableFoodUiState.food
    val spacing = LocalSpacing.current


    Column(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(spacing.spaceExtraSmall)
            .shadow(elevation = 1.dp, shape = RoundedCornerShape(5.dp))
            .background(MaterialTheme.colors.surface)
            .clickable { onClick() }
            .padding(end = spacing.spaceMedium)
    ) {
        //Row 1
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            //weight enables c1 & c2 to expand as required while the other children
            //outside this row take the the left over space

            //Row 1a
            Row(modifier = Modifier.weight(1f)) {
                //c1
                Image(painter = rememberImagePainter(data = food.imageUrl, builder = {
                    crossfade(true)
                    //if there is a serve error - no connection etc
                    error(R.drawable.ic_burger)
                    //If the data param is null load the image from the fallback params
                    fallback(R.drawable.ic_burger)

                }
                ),
                    contentDescription = food.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(
                            RoundedCornerShape(topStart = 5.dp)
                        ))

                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                //c2
                Column() {
                    Text(
                        text = food.name,
                        overflow = TextOverflow.Visible,
                        style = MaterialTheme.typography.body1,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(spacing.spaceSmall))
                    //Text(text = food.caloriesPer100g.toString())
                    Text(
                        text = stringResource(id = R.string.kcal_per_100g, food.caloriesPer100g),
                        style = MaterialTheme.typography.body2
                    )
                }
            }

            //Row 1b
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {

                //c3
                NutrientsInfo(
                    name = stringResource(id = R.string.carbs),
                    amount = food.carbsPer100g,
                    unit = stringResource(
                        id = R.string.grams
                    ),
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.body2
                )

                //c4
                NutrientsInfo(
                    name = stringResource(id = R.string.protein),
                    amount = food.proteinPer100g,
                    unit = stringResource(
                        id = R.string.grams
                    ),
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.body2
                )

                //c5
                NutrientsInfo(
                    name = stringResource(id = R.string.fat),
                    amount = food.fatsPer100g,
                    unit = stringResource(
                        id = R.string.grams
                    ),
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.body2
                )

            }




        }
        //Row 2
        Row() {

        }
    }

}