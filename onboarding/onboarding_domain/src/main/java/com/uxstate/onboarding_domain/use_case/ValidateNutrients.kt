package com.uxstate.onboarding_domain.use_case


//validates the TextFields values and ensures they add up to 100
class ValidateNutrients {

    //invoke takes 3 parameters needed for its logic
    operator fun invoke(
        carbsRatioText: String,
        proteinsTextRatio: String,
        fatsRatioText: String
    ) {


    }

    //class representing the validation result to notify ViewModel
    //directly from the use case

    sealed class Result {}
}