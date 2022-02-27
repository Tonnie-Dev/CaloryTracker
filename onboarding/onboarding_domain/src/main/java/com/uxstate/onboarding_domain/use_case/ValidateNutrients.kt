package com.uxstate.onboarding_domain.use_case

import com.uxstate.core.util.UiText


//validates the TextFields values and ensures they add up to 100
class ValidateNutrients {

    //invoke takes 3 parameters needed for its logic & returns a Result Object
    operator fun invoke(
        carbsRatioText: String,
        proteinsTextRatio: String,
        fatsRatioText: String
    ):Result {


    }

    //class representing the validation result to notify ViewModel
    //directly from the use case

    sealed class Result {

        data class Success(val carbsRatio: Int, val proteinsRatio: Int, val fatsRatio: Int) :
            Result() {


        }

        data class Error(val message: UiText) : Result()
    }
}