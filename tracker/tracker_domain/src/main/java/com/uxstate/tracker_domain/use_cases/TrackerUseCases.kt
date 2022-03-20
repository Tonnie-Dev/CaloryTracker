package com.uxstate.tracker_domain.use_cases


//helps make the viewModel not have many constructor injections
data class TrackerUseCases(
    val calculateMealNutrientsUseCase: CalculateMealNutrientsUseCase,
    val deleteFoodUseCase: DeleteFoodUseCase,
    val getFoodsForDateUseCase: GetFoodsForDateUseCase,
    val searchFoodUseCase: SearchFoodUseCase,
    val trackFoodUseCase: TrackFoodUseCase
)
