package com.uxstate.tracker_presentation.tracker_overview.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberImagePainter
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.tracker_domain.model.TrackedFood

@Composable
fun TrackedFoodItem(trackedFood: TrackedFood, onDelete: () -> Unit, modifier: Modifier = Modifier) {

    val spacing = LocalSpacing.current


    Surface() {
        Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            //image - square
            Image(
                painter = rememberImagePainter(data =),
                contentDescription = "",
                modifier = Modifier.aspectRatio(1f)
            )
            //Column 1
            Column() {

                Text(
                    text = trackedFood.name,
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis
                )

            }

            //Column 2
            Column() {}


        }
    }

}