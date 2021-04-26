package com.sebasorozcob.www.restlov.data

import com.sebasorozcob.www.restlov.data.network.RestaurantsAPI
import com.sebasorozcob.www.restlov.model.Restaurant
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val restaurantsAPI: RestaurantsAPI
) {
    suspend fun getRestaurants(
        longitude: String,
        latitude: String,
        restaurantName: String?
    ): Response<Restaurant> {
        return restaurantsAPI.getRestaurants(longitude, latitude, restaurantName)
    }
}