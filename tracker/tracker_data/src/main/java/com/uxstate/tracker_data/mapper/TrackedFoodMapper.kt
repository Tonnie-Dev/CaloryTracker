package com.uxstate.tracker_data.mapper

import com.uxstate.tracker_data.local.entity.TrackedFoodEntity
import com.uxstate.tracker_domain.model.MealType
import com.uxstate.tracker_domain.model.TrackedFood
import java.time.LocalDate


fun TrackedFoodEntity.toTrackedFood(): TrackedFood {
    
    return TrackedFood(
        name = this.name,
        carbs = this.carbs,
        protein = this.protein,
        fat = this.fats,
        imageUrl = this.imageUrl,
        mealType = MealType.fromString(this.type),
        amount = this.amount,
        date = LocalDate.of(year, month, dayOfMonth),
        calories = this.calories,
        id = this.id
    
    )
}


fun TrackedFood.toTrackedFoodEntity(): TrackedFoodEntity {
    
    return TrackedFoodEntity(
        name = this.name,
        carbs = this.carbs,
        protein = this.protein,
        fats = this.fat,
        imageUrl = this.imageUrl,
        type = this.mealType.name,
        amount = this.amount,
        dayOfMonth = this.date.dayOfMonth,
        month = this.date.monthValue,
        year = this.date.year,
        calories = this.calories,
        id = this.id
    
    )
}