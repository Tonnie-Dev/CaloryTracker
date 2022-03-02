package com.uxstate.tracker_data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uxstate.tracker_data.local.TrackerDao
import com.uxstate.tracker_data.local.entity.TrackedFoodEntity

@Database(entities = [TrackedFoodEntity::class], version = 1)
abstract class TrackerDatabase: RoomDatabase() {

    abstract val dao:TrackerDao
}