package com.uxstate.tracker_presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.uxstate.core.util.UIEvent
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.tracker_presentation.R
import com.uxstate.tracker_presentation.search.components.SearchTextField

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
    mealName:String,
    dayOfMonth: Int,
    month: Int,
    year:Int,
    onNavigateUp: () -> Unit
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val state = viewModel.state
    val keyboardController = LocalSoftwareKeyboardController.current


    //launched block to listen to the viewModel events

    LaunchedEffect(key1 = keyboardController) {

        viewModel.uiEvent.collect {

                event ->

            when (event) {

                is UIEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(
                            context = context
                        )
                    )

                    //hide keyboard so that it doesn't overlap the snackbar

                    keyboardController?.hide()
                }
                is UIEvent.NavigateUp -> {

                    onNavigateUp()
                }
                else -> Unit
            }
        }
    }


    //column to hold all the other contents of the searche screen
    Column(
        modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceMedium)
    ) {

        Text(text = stringResource(id = R.string.add_meal,mealName), style = MaterialTheme.typography.h2)

        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        SearchTextField(
            text = state.query,
            hint = stringResource(id = R.string.search),
            onValueChange = {
                            viewModel.onEvent(SearchEvent.OnQueryChange(it))
            } ,
            onSearch = { viewModel.onEvent(SearchEvent.OnSearch)},
            onFocusChange = {
                viewModel.onEvent(SearchEvent.OnSearchFocusChange(it.isFocused))}
        )
    }

}