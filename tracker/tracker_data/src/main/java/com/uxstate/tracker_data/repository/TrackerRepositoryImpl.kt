package com.uxstate.tracker_data.repository

import com.uxstate.tracker_data.local.TrackerDao
import com.uxstate.tracker_data.mapper.toTrackableFood
import com.uxstate.tracker_data.mapper.toTrackedFood
import com.uxstate.tracker_data.mapper.toTrackedFoodEntity
import com.uxstate.tracker_data.remote.OpenFoodAPI
import com.uxstate.tracker_domain.model.TrackableFood
import com.uxstate.tracker_domain.model.TrackedFood
import com.uxstate.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class TrackerRepositoryImpl(private val dao: TrackerDao, private val api: OpenFoodAPI) :
    TrackerRepository {

    //GET FOOD FROM API
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {

        //handle exception and map to model classess
        return try {

            //searchDTO holds a list of Products
            val searchDTO = api.searchFood(query = query, page = page, pageSize = pageSize)


            //map List<Products> to List<TrackableFood> but skip null objects with MapNotNull
            val trackableFoodList =
                searchDTO.products.mapNotNull { product -> product.toTrackableFood() }
            //return a list of TrackableFood

            //map
            Result.success(trackableFoodList)

        } catch (e: Exception) {

            e.printStackTrace()
            Result.failure(e)
        }

    }


    //INSERT_INTO_DB
    override suspend fun insertTrackedFood(trackedFood: TrackedFood) {
        dao.insertTrackedFood(trackedFood.toTrackedFoodEntity())
    }


    //DELETE_FROM_DB
    override suspend fun deleteTrackedFood(trackedFood: TrackedFood) {
        dao.deleteTrackedFood(trackedFood.toTrackedFoodEntity())
    }

    //EXPOSE_DATA_TO_USE_CASE_AS_FLOW
    override fun getFoodForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodForDate(
            day = localDate.dayOfMonth,
            month = localDate.monthValue,
            year = localDate.year

        //mapping the returned flow
        ).map {
            entities ->

            //mapping the returned list
            entities.map {

                it.toTrackedFood()
        }
        }
    }
}