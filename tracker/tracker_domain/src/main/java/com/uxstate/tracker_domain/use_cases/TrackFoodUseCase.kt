package com.uxstate.tracker_domain.use_cases

import com.uxstate.tracker_domain.repository.TrackerRepository

class TrackFoodUseCase(private val repository: TrackerRepository) {

    suspend operator fun invoke(){}

}