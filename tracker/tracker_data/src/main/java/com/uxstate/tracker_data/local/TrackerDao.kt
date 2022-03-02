package com.uxstate.tracker_data.local

import androidx.room.*
import com.uxstate.tracker_data.local.entity.TrackedFoodEntity

@Dao
interface TrackerDao {
/*when there is already an item in our db that has the same id
as the item that wee need to insert, then we simply replace it*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)

    //we use suspend to execute the insert fxn inside a coroutine
    suspend fun insertTrackedFood(trackedFoodEntity: TrackedFoodEntity)

    @Delete
    suspend fun deleteTrackedFood(trackedFoodEntity:TrackedFoodEntity)

  /*normal fxn as it returns a flow as opposed to suspend function
  * it is triggered everytime there is a change in the database*/


    @Query("""
         SELECT * FROM trackedfoodentity
         WHERE dayOfMonth =:day AND month =:month AND year = :year
    """

        
    )
    fun getFoodForDate(day:Int, month:Int, year:Int): TrackedFoodEntity
}