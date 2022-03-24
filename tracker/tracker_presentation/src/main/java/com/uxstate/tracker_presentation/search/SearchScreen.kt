package com.uxstate.tracker_presentation.search

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.uxstate.tracker_presentation.R
import com.uxstate.tracker_presentation.search.components.SearchTextField

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    onNavigateUp: () -> Unit
) {

    SearchTextField(
        text = viewModel.state.query,
        hint = stringResource(id = R.string.search),
        onValueChange = viewModel.onEvent(event = SearchEvent.OnSearch()),
        onSearch = { /*TODO*/ },
        onFocusChange =
    )

}