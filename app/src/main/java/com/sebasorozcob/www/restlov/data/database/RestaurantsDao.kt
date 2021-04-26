package com.sebasorozcob.www.restlov.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sebasorozcob.www.restlov.data.database.entities.RestaurantsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RestaurantsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRestaurant(restaurantsEntity: RestaurantsEntity)


    @Query("SELECT * FROM restaurants_table ORDER BY id ASC")
    fun readRestaurants(): Flow<List<RestaurantsEntity>>

}