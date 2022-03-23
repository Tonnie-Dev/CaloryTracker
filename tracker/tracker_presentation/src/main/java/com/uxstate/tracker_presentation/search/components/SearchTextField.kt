package com.uxstate.tracker_presentation.search.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import com.uxstate.core_ui.LocalSpacing

@Composable
fun SearchTextField(
    text: String,
    hint: String,
    onValueChange: () -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier,
    shouldShowHint: Boolean = false, 
    onFocusChange: (FocusState) -> Unit
) {

    val spacing = LocalSpacing.current
    
    Box() {
        
        BasicTextField(value = , onValueChange = )
    }
}