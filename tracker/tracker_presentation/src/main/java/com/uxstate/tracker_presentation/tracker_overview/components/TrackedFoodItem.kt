package com.uxstate.tracker_presentation.tracker_overview.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.tracker_domain.model.TrackedFood
import com.uxstate.tracker_presentation.R

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

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        //image - square
        Image(
            painter = rememberImagePainter(data = trackedFood.imageUrl, builder = {
                crossfade(true)
                error(R.drawable.ic_burger)
                fallback(R.drawable.ic_burger)
            }),
            contentDescription = trackedFood.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxHeight().aspectRatio(1f).clip(RoundedCornerShape(topStart = 5.dp, bottomStart = 5.dp))
        )
        //Column 1
        Column() {

            Text(
                text = trackedFood.name,
                style = MaterialTheme.typography.body2,
                overflow = TextOverflow.Ellipsis
            )


            //Column 2
            Column() {}


        }
    }

}