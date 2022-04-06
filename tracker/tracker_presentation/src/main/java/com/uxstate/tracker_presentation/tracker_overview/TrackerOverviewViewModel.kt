package com.uxstate.tracker_presentation.tracker_overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uxstate.core.domain.preferences.Preferences
import com.uxstate.core.navigation.Route
import com.uxstate.core.util.UIEvent
import com.uxstate.tracker_domain.use_cases.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
    private val trackerUseCases: TrackerUseCases,
    prefs: Preferences
) :
    ViewModel() {

    //STATES
    var state by mutableStateOf(TrackerOverviewState())
        private set

    //GLOBAL VARIABLE
    private var getFoodForDateJob: Job? = null

    //EVENTS
    private val _uiEvent = Channel<UIEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {

        //make the onboarding not show again
        prefs.saveShouldShowOnboarding(false)

        //refresh on viewModel's initialization
        refreshFoods()

    }


    fun onEvent(event: TrackerOverViewEvent) {


        //propagate state to the UI
        when (event) {

            // //navigate to search screen with the given meal and date
            is TrackerOverViewEvent.OnAddFoodClick -> {

                //we cannot pass a local date on navigation therefore we pass it as a String
                viewModelScope.launch {

                    _uiEvent.send(UIEvent.Successs)
                    /*_uiEvent.send(
                        UIEvent.Successs(
                            Route.SEARCH +
                                    "/${event.meal.mealType.name}" + //lunch, breakfast etc
                                    "/${state.date.dayOfMonth}" +  //today date from the state class
                                    "/${state.date.monthValue}" +  //current month
                                    "/${state.date.year}"           //current year
                        )
                    )*/
                }

            }
            is TrackerOverViewEvent.OnDeleteTrackedFoodClick -> {

                //execute delete user case

                viewModelScope.launch {

                    trackerUseCases.deleteFoodUseCase(event.food)

                    /*deleting the food changes our state and we
                    need to re-calculate all the calories therefore
                    we need some kind of a function to refresh the entire state*/

                    refreshFoods()
                }


            }
            is TrackerOverViewEvent.OnNextDayClick -> {
                state = state.copy(date = state.date.plusDays(1))
                refreshFoods()
            }

            is TrackerOverViewEvent.OnPreviousDayClick -> {

                state = state.copy(date = state.date.minusDays(1))

                refreshFoods()
            }
            is TrackerOverViewEvent.OnToggleMealClick -> {

                //use map to transform
                state = state.copy(meals = state.meals.map {

                    //find the meal item that was toggled
                    if (it.name == event.meal.name){
                     it.copy(isExpanded = !it.isExpanded)

                        //else leave the meal as it is
                    }else it

                })
            }


        }

    }

    private fun refreshFoods() {

        /*since refreshFoods() will trigger a new a flow every single time,
        we need to cancel the old flow*/

        //cancel the running job before with start a new one

        getFoodForDateJob?.cancel()

        //start a new job here

        //access foods from db for a given date to calculate nutrients
        //getFoodsForDateUseCase returns a flow - Flow<List<TrackedFood>>

        //new flow starts here
        getFoodForDateJob = trackerUseCases.getFoodsForDateUseCase(state.date)

                //we get an emission of List<TrackedFood> which we iterate on and update state
                .onEach {


                    //calculate goals, total consumed, map<MealType, MealNutrients> from the use case
                    val nutrientsResult = trackerUseCases.calculateMealNutrientsUseCase(it)

                    //update state

                    state = state.copy(

                        //consumed - re-calculation from the use case
                        totalCarbs = nutrientsResult.totalCarbs,
                        totalProteins = nutrientsResult.totalProteins,
                        totalFats = nutrientsResult.totalFats,
                        totalCalories = nutrientsResult.totalCalories,

                        //goals -  re-calculation from the use case
                        carbsGoal = nutrientsResult.carbsGoal,
                        proteinsGoal = nutrientsResult.proteinsGoal,
                        fatsGoal = nutrientsResult.fatsGoal,
                        caloriesGoal = nutrientsResult.caloriesGoal,

                        //tracked food from the db for a given date
                        trackedFoods = it,

                        /*retrieve meals info from use case and update meals list UI display
                        - we take the current meals list and map (transform) it to new values*/

                        meals = state.meals.map { meal ->
                            /*this give us all the meals for a specific meal type,
                            but if meal is null, set it to default
                         */

                            //use_case.Map<MealType, MealNutrients>

                            // mealNutrients stores nutrients for a specific meal type e.g. breakfast


                            val nutrientsForMeal = nutrientsResult.mealNutrients[meal.mealType]
                            //null-check - returns a default Meal object if null
                                ?: return@map meal.copy(
                                    carbs = 0,
                                    proteins = 0,
                                    fats = 0,
                                    calories = 0
                                )

                            //implicit return by map function
                            //if we didn't return from above, then it means there are meals found
                            meal.copy(
                                carbs = nutrientsForMeal.carbs,
                                proteins = nutrientsForMeal.proteins,
                                fats = nutrientsForMeal.fats,
                                calories = nutrientsForMeal.calories

                            )
                        }
                    )
                }
                .launchIn(viewModelScope)
    }
}