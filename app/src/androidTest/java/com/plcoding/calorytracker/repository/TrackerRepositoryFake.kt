package com.plcoding.calorytracker.repository

import com.uxstate.tracker_domain.model.TrackableFood
import com.uxstate.tracker_domain.model.TrackedFood
import com.uxstate.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import kotlin.random.Random

//implements repository interface which is handy in testing

//simulates the behaviour of the real repository but this faster
//by saving everything in memory instead of using the real one
//which could be affected by things like the server being down
//or connection problem which don't indicate a mistake on our code
class TrackerRepositoryFake : TrackerRepository {
    
    val shouldReturnError = false
    
    //simulated list of TrackedFood from the database
    private val trackedFood =
        mutableListOf<TrackedFood>() //public to enable setting up of the list from outside
    
    val searchResults = listOf<TrackableFood>()
    
    
    override suspend fun searchFood(
        query: String, page: Int, pageSize: Int
    ): Result<List<TrackableFood>> {
        return if (shouldReturnError) Result.failure(Throwable())
        else Result.success(searchResults)
    }
    
    override suspend fun insertTrackedFood(trackedFood: TrackedFood) {
        
        //simulates the db insertion but doesn't autogenerate ids
        // so we generate a random id
        this.trackedFood.add(trackedFood.copy(id = Random.nextInt()))
    }
    
    override suspend fun deleteTrackedFood(trackedFood: TrackedFood) {
        TODO("Not yet implemented")
    }
    
    override fun getFoodForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        TODO("Not yet implemented")
    }
}