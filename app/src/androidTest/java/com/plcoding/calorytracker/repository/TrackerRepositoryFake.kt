package com.plcoding.calorytracker.repository

import com.uxstate.tracker_domain.model.TrackableFood
import com.uxstate.tracker_domain.model.TrackedFood
import com.uxstate.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class TrackerRepositoryFake: TrackerRepository {
    override suspend fun searchFood(
        query: String, page: Int, pageSize: Int
    ): Result<List<TrackableFood>> {
        TODO("Not yet implemented")
    }
    
    override suspend fun insertTrackedFood(trackedFood: TrackedFood) {
        TODO("Not yet implemented")
    }
    
    override suspend fun deleteTrackedFood(trackedFood: TrackedFood) {
        TODO("Not yet implemented")
    }
    
    override fun getFoodForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        TODO("Not yet implemented")
    }
}