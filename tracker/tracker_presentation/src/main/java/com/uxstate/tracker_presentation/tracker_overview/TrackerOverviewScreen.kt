package com.uxstate.tracker_presentation.tracker_overview

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.uxstate.core.util.UIEvent
import com.uxstate.core_ui.LocalSpacing
import com.uxstate.tracker_presentation.R
import com.uxstate.tracker_presentation.tracker_overview.components.*


@Composable
fun TrackerOverviewScreen(
    onNavigateToSearch: (String, Int, Int, Int) -> Unit,
    viewModel: TrackerOverviewViewModel = hiltViewModel()
) {

    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val state = viewModel.state
//Launch Block has no use

/*    LaunchedEffect(key1 = true, block = {

        viewModel.uiEvent.collect { event ->

            when (event) {

                is UIEvent.Successs -> {
                    // onNavigateToSearch()

                }
                else -> Unit
            }
        }
    })*/

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = spacing.spaceMedium),
        content = {


            item {

                NutrientsHeader(state = state)
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                DaySelector(
                    localDate = state.date,
                    onPreviousDayClick = {

                        viewModel.onEvent(TrackerOverViewEvent.OnPreviousDayClick)
                    },
                    onNextDayClick = {
                        viewModel.onEvent(TrackerOverViewEvent.OnNextDayClick)

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = spacing.spaceMedium)
                )


                Spacer(modifier = Modifier.height(spacing.spaceMedium))
            }


            //pass in the meals list -Breakfast, Lunch, Dinner, Snack
            items(state.meals) { meal ->

                ExpandableMeal(
                    meal = meal,
                    onToggleClick = {

                        //send an event to the ViewModel from the UI
                        viewModel.onEvent(TrackerOverViewEvent.OnToggleMealClick(meal = meal))
                    },
                    content = {

                        //column to hold tracked food items and button

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = spacing.spaceSmall)
                        ) {


                            //filter all tracked foods by meal type
                            
                            val foods = state.trackedFoods.filter {
                                    //current meal from the expandable composable
                                    it.mealType ==meal.mealType
                            
                            }
                            //iterate on tracked food items from state

                            foods.forEach {

                                    item ->
                                TrackedFoodItem(
                                    trackedFood = item,
                                    onDelete = {
                                        viewModel.onEvent(
                                            TrackerOverViewEvent.OnDeleteTrackedFoodClick(item)
                                        )
                                    })

                                //add space after each item
                                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                            }

                            //button
                            AddButton(

                                //text uses placeholder for dinner, lunch, snacks etc
                                text = stringResource(
                                    id = R.string.add_meal,
                                    meal.name.asString(context = context)
                                ),
                                onClick = {
                                    /*  viewModel.onEvent(
                                          TrackerOverViewEvent.OnAddFoodClick(meal = meal)
                                      )*/

                                    onNavigateToSearch(
                                        meal.name.asString(context = context),
                                        state.date.dayOfMonth,
                                        state.date.monthValue,
                                        state.date.year
                                    )
                                }, modifier = Modifier.fillMaxWidth()
                            )
                        }

                    },
                    modifier = Modifier.fillMaxWidth()
                )


            }
        })


}

