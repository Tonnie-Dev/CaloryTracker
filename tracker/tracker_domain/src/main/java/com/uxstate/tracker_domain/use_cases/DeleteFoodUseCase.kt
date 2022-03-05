package com.uxstate.tracker_domain.use_cases

import com.uxstate.tracker_domain.model.TrackedFood
import com.uxstate.tracker_domain.repository.TrackerRepository

class DeleteFoodUseCase(private val repository: TrackerRepository) {


    suspend operator fun invoke(trackedFood: TrackedFood){

        repository.deleteTrackedFood(trackedFood)
    }
}