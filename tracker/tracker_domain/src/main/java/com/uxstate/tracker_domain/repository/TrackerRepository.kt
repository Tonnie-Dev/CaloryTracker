package com.uxstate.tracker_domain.repository

import com.uxstate.tracker_domain.model.TrackableFood
import com.uxstate.tracker_domain.model.TrackedFood
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TrackerRepository {

    //returns from API thus the Result<> return type
    suspend fun searchFood(query:String, page:Int, pageSize:Int):Result<List<TrackableFood>>

    suspend fun insertTrackedFood( trackedFood: TrackedFood)

    suspend fun deleteTrackedFood(trackedFood: TrackedFood)
//retrieve from the db
    fun getFoodForDate(localDate: LocalDate): Flow<List<TrackedFood>>


}