package com.uxstate.tracker_domain.repository

import com.uxstate.tracker_domain.model.TrackableFood

interface TrackerRepository {

    suspend fun searchFood(query:String, page:Int, pageSize:Int):Result<List<TrackableFood>>
}