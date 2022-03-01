package com.uxstate.tracker_data.local.db

import androidx.room.RoomDatabase
import com.uxstate.tracker_data.local.TrackerDao

abstract class FoodTrackerDatabase: RoomDatabase() {

    abstract val dao:TrackerDao
}