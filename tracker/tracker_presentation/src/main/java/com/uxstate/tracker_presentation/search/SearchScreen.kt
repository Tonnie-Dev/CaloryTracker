package com.uxstate.tracker_presentation.search

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import com.uxstate.core.util.UIEvent
import com.uxstate.core_ui.LocalSpacing
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState,
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
            
            when(event){

                is UIEvent.ShowSnackbar -> {}
                is UIEvent.NavigateUp -> {}
                else -> Unit
            }
        }
    }

}