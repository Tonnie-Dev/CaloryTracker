package com.uxstate.tracker_presentation.tracker_overview.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.tracker_domain.model.TrackedFood
import com.uxstate.tracker_presentation.R
import com.uxstate.tracker_presentation.components.NutrientsInfo

@OptIn(ExperimentalCoilApi::class)
@Composable
fun TrackedFoodItem(
    trackedFood: TrackedFood,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {

    val spacing = LocalSpacing.current

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(spacing.spaceExtraSmall)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(5.dp)
            )
            .background(MaterialTheme.colors.surface)
            .padding(end = spacing.spaceMedium)
            .height(100.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        //image - square
        Image(
            painter = rememberImagePainter(
                data = trackedFood.imageUrl,
                builder = {


                    crossfade(true)

                    //server error
                    error(R.drawable.ic_burger)

                    ///*If the data param is null when you initialize
                    // rememberImagePainter it will load the image
                    // from the fallback params*/

                    fallback(R.drawable.ic_burger)
                }
            ),
            contentDescription = trackedFood.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
                .clip(
                    RoundedCornerShape(
                        topStart = 5.dp,
                        bottomStart = 5.dp
                    )
                )
        )

        Spacer(modifier = Modifier.width(spacing.spaceMedium))
        //Column 1
        Column(modifier = Modifier.weight(1f)) {

            Text(
                text = trackedFood.name,
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )


            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))

            Text(
                //formatted amount Text
                text = stringResource(
                    id = R.string.nutrient_info,
                    trackedFood.amount,
                    trackedFood.calories
                ), style = MaterialTheme.typography.body2
            )}
            Spacer(modifier = Modifier.width(spacing.spaceMedium))

            //Column 2
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.delete),
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable { onDelete() })

                Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))

                Row(verticalAlignment = Alignment.CenterVertically) {

                    NutrientsInfo(
                        name = stringResource(id = R.string.carbs),
                        amount = trackedFood.carbs,
                        unit = stringResource(
                            id = R.string.grams
                        ),

                        amountTextSize = 16.sp,
                        unitTextSize = 12.sp,
                        nameTextStyle = MaterialTheme.typography.body2
                    )

                    Spacer(modifier = Modifier.width(spacing.spaceSmall))
                    NutrientsInfo(
                        name = stringResource(id = R.string.protein),
                        amount = trackedFood.protein,
                        unit = stringResource(
                            id = R.string.grams
                        ),
                        amountTextSize = 16.sp,
                        unitTextSize = 12.sp,
                        nameTextStyle = MaterialTheme.typography.body2
                    )

                    Spacer(modifier = Modifier.width(spacing.spaceSmall))
                    NutrientsInfo(
                        name = stringResource(id = R.string.fat),
                        amount = trackedFood.fat,
                        unit = stringResource(
                            id = R.string.grams
                        ),
                        amountTextSize = 16.sp,
                        unitTextSize = 12.sp,
                        nameTextStyle = MaterialTheme.typography.body2
                    )
                }

            }


        }
    }

