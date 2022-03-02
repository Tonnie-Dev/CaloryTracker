package com.uxstate.tracker_domain.repository

import com.uxstate.tracker_domain.model.TrackableFood
import com.uxstate.tracker_domain.model.TrackedFood
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TrackerRepository {

    suspend fun searchFood(query:String, page:Int, pageSize:Int):Result<List<TrackableFood>>

    suspend fun insertTrackedFood( trackableFood: TrackableFood)

    suspend fun deleteTrackedFood(trackedFood: TrackedFood)

    fun getFoodForDate(localDate: LocalDate): Flow<List<TrackedFood>>


}