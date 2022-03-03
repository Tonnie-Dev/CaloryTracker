package com.uxstate.tracker_data.repository

import com.uxstate.tracker_data.local.TrackerDao
import com.uxstate.tracker_data.remote.OpenFoodAPI
import com.uxstate.tracker_domain.model.TrackableFood
import com.uxstate.tracker_domain.model.TrackedFood
import com.uxstate.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class TrackerRepositoryImpl(private val dao: TrackerDao, private val api: OpenFoodAPI) :
    TrackerRepository {
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {

        return try {

            val searchDTO = api.searchFood(query = query, page = page, pageSize = pageSize)
            Result.success()

        } catch (e: Exception) {

            e.printStackTrace()
            Result.failure(e)
        }

    }

    override suspend fun insertTrackedFood(trackableFood: TrackableFood) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTrackedFood(trackedFood: TrackedFood) {
        TODO("Not yet implemented")
    }

    override fun getFoodForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        TODO("Not yet implemented")
    }
}