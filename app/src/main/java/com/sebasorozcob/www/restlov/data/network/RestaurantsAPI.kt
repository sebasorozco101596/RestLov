package com.sebasorozcob.www.restlov.data.network
import com.sebasorozcob.www.restlov.model.Restaurant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import javax.inject.Qualifier

interface RestaurantsAPI {

    @GET("/restaurants/close")
    suspend fun getRestaurants(
        @Query("longitude") longitude: String,
        @Query("latitude") latitude : String,
        @Query("restaurant") restaurantName: String?
    ): Response<Restaurant>
}
