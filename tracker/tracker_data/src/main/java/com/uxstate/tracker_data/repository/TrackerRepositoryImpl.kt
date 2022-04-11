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

        //handle exception and map to model classes
        return try {

            //searchDTO holds a list of Products
            val searchDTO = api.searchFood(query = query, page = page, pageSize = pageSize)


            val trackableFoodList = searchDTO.products
                //introduce filter to check for accurascy
                .filter {

                    //variable to calculate product's calories
                    val calculatedCalories =
                        (it.nutriments.carbohydrates100g * 4f) +
                                (it.nutriments.proteins100g * 4f) +
                                (it.nutriments.fat100g * 9f)

                    //ranges
                    val upperCut = calculatedCalories * 1.01f
                    val lowerCut = calculatedCalories * 0.99f

                    // apply filter
                    it.nutriments.energyKcal100g in (lowerCut..upperCut)

                    /*if the product's calories are inside of the specified range,
                    * then we know that the values for that given food have been properly
                    * calculated and we want to include them in our list. if not we
                    * did not include the item in our list*/

                }


                //map List<Products> to List<TrackableFood> but skip null objects with MapNotNull
                .mapNotNull { product -> product.toTrackableFood() }
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
        ).map { entities ->

            //mapping the returned list
            entities.map {

                it.toTrackedFood()
            }
        }
    }
}