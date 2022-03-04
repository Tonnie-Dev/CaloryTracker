package com.uxstate.tracker_data.mapper

import com.uxstate.tracker_data.remote.dto.Product
import com.uxstate.tracker_domain.model.TrackableFood
import kotlin.math.roundToInt

fun Product.toTrackableFood():TrackableFood?{


    return TrackableFood(

        //roundToInt - rounds to the nearest int
        name = this.productName ?: return null,
        carbsPer100g = this.nutriments.carbohydrates100g.roundToInt(),
        proteinPer100g = this.nutriments.proteins100g.roundToInt(),
        fatsPer100g = this.nutriments.fat100g.roundToInt(),
        imageUrl = this.imageFrontThumbUrl,
        caloriesPer100g = this.nutriments.energyKcal100g.roundToInt()
    )

    /*if the name is null, then we return the entire TrackableFood as null*/
}