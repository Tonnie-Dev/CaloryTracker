package com.uxstate.tracker_domain.model
//API
data class TrackableFood(
    val name: String,
    val carbsPer100g: Int,
    val proteinPer100g: Int,
    val fatsPer100g: Int,
    val imageUrl: String?,
    val caloriesPer100g: Int
)
