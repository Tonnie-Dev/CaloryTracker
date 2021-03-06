package com.uxstate.tracker_data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.uxstate.tracker_domain.model.MealType
import java.time.LocalDate

@Entity()
data class TrackedFoodEntity(

    //room autogenerates this for us
    @PrimaryKey val id: Int? = null,
    val name: String,
    val carbs: Int,
    val protein: Int,
    val fats: Int,
    val imageUrl: String?,
    val type: String,
    val amount: Int,
    val dayOfMonth: Int,
    val month: Int,
    val year: Int,
    val calories: Int



    //



   /* val mealType: MealType,
    val date: LocalDate,*/

)
