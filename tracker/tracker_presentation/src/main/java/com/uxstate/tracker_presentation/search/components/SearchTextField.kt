package com.uxstate.tracker_presentation.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.tracker_presentation.R

@Composable
fun SearchTextField(
    text: String,
    hint: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
    shouldShowHint: Boolean = false,
    onFocusChange: (FocusState) -> Unit
) {

    val spacing = LocalSpacing.current

    Box(modifier = modifier) {

        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            singleLine = true,

            //configures the keyboard to define action to display
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),

            //defines what a keyboard action does when invoked
            keyboardActions = KeyboardActions(onSearch = {
                onSearch()
                //execute the default behaviour clicking search icon on the soft keyboard
                defaultKeyboardAction(ImeAction.Search)
            }),

            modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .padding(2.dp)
                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(5.dp))
                    .background(MaterialTheme.colors.surface)
                    .fillMaxWidth()
                    .padding(spacing.spaceMedium)
                    //help to prevent long search word
                    .padding(spacing.spaceMedium)
                    //trigger our onFocusChange() with the new focus
                    .onFocusChanged { onFocusChange(it) }


        )

        //show hint on top of the TextField

        if (shouldShowHint) {

            Text(
                text = hint,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Light,
                color = Color.Gray,
                modifier = Modifier
                        .align(
                            Alignment.CenterStart
                        )
                        .padding(start = spacing.spaceMedium)
            )
        }

        //trailing icon

        IconButton(
            onClick = onSearch,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search)
            )
        }
    }
}