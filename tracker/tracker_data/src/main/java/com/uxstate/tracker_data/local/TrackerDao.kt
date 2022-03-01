package com.uxstate.tracker_data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface TrackerDao {
/*when there is already an item in our db that has the same id
as the item that wee need to insert, then we simply replace it*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)

    //we use suspend to execute the insert fxn inside a coroutine
    suspend fun insertTrackedFood(tra)
}