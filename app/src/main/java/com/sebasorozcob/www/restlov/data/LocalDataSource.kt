package com.sebasorozcob.www.restlov.data

import com.sebasorozcob.www.restlov.data.database.RestaurantsDao
import com.sebasorozcob.www.restlov.data.database.entities.RestaurantsEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val restaurantsDao: RestaurantsDao
) {
    fun readRestaurants(): Flow<List<RestaurantsEntity>> {
        return restaurantsDao.readRestaurants()
    }
}