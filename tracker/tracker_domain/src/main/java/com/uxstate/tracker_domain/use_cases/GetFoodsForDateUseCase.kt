package com.uxstate.tracker_domain.use_cases

import com.uxstate.tracker_domain.model.TrackedFood
import com.uxstate.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate


//retrieve food from the DB
class GetFoodsForDateUseCase (private val repository: TrackerRepository){


operator fun invoke(date:LocalDate): Flow<List<TrackedFood>> {

    return repository.getFoodForDate(date)
}
}