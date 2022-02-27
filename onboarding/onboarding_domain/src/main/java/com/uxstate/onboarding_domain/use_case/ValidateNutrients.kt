package com.uxstate.onboarding_domain.use_case

import com.uxstate.core.util.UiText
import com.uxstate.onboarding_domain.R


//validates the TextFields values and ensures they add up to 100
class ValidateNutrients {

    //invoke takes 3 parameters needed for its logic & returns a Result Object
    operator fun invoke(
        carbsRatioText: String,
        proteinsRatioText: String,
        fatsRatioText: String
    ): Result {

        //parse parameters to ints

        val carbsRatio = carbsRatioText.toIntOrNull()
        val proteinsRatio = proteinsRatioText.toIntOrNull()
        val fatsRatio = fatsRatioText.toIntOrNull()

        //check for nulls

        if (carbsRatio == null || proteinsRatio == null || fatsRatio == null) {

            //respond to the ViewModel with an error
            return Result.Error(message = UiText.StringResource(R.string.error_invalid_values))
        }

        //check for 100% toal
        else if ((carbsRatio + proteinsRatio + fatsRatio) != 100) {

            //respond to the ViewModel with an error
            return Result.Error(message = UiText.StringResource(R.string.error_not_100_percent))

            //else success
        } else {

            return Result.Success(
                carbsRatio = carbsRatio,
                proteinsRatio = proteinsRatio,
                fatsRatio = fatsRatio
            )
        }

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