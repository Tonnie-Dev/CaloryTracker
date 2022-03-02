package com.uxstate.tracker_domain.model

import java.time.LocalDate

//db
data class TrackedFood(
    val name: String,
    val carbs: Int,
    val protein: Int,
    val fat: Int,
    val imageUrl: String?,
    val mealType: MealType,
    val amount:Int,
    val date: LocalDate,
    val calories:Int,
    val id:Int? = null
)

/*LocalDate class gives us more options to manipulate a date*/