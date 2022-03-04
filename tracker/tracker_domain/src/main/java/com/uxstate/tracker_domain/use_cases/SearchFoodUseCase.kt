package com.uxstate.tracker_domain.use_cases

import com.uxstate.tracker_domain.model.TrackableFood
import com.uxstate.tracker_domain.repository.TrackerRepository

//use-case should be accessing UI or Data(DTOs, DB etc)

//The repository passed in is the definitions inside the domain layer
class SearchFoodUseCase(private val repository: TrackerRepository) {

    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        pageSize: Int = 40
    ): Result<List<TrackableFood>> {


        //check if the query is blank and return an empty list
        if (query.isBlank()) {

            return Result.success(listOf())
        }

        //trim() eliminates leading and trailing spaces
        return repository.searchFood(query = query.trim(), page = page, pageSize = pageSize)


    }

}