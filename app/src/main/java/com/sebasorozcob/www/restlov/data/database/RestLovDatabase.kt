package com.sebasorozcob.www.restlov.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sebasorozcob.www.restlov.data.database.entities.RestaurantsEntity

@Database(
    entities = [RestaurantsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RestLovDatabase : RoomDatabase() {

    abstract fun restaurantsDao(): RestaurantsDao
}