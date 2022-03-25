package com.sebasorozcob.www.restlov.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.*
import com.sebasorozcob.www.restlov.data.Repository
import com.sebasorozcob.www.restlov.data.database.entities.RestaurantsEntity
import com.sebasorozcob.www.restlov.model.Restaurant
import com.sebasorozcob.www.restlov.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    /** ROOM DATABASE */
    val readRestaurants: LiveData<List<RestaurantsEntity>> = repository.local.readRestaurants().asLiveData()


    /** RETROFIT */
    var restaurantsResponse: MutableLiveData<NetworkResult<Restaurant>> = MutableLiveData()

    fun getRestaurants(
        longitude: String,
        latitude: String,
        restaurantName: String?
    ) =
        viewModelScope.launch {
            getRestaurantsSafeCall(longitude,latitude,restaurantName)
        }
    /**
     * This function get the restaurants if the phone has internet for stay sure to get the information
     */
    private suspend fun getRestaurantsSafeCall(
        longitude: String,
        latitude: String,
        restaurantName: String?
    ) {
        restaurantsResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getRestaurants(longitude, latitude, restaurantName)
                restaurantsResponse.value = handleRestaurantResponse(response)

                // Later of the app get the information of the API save it in the database
                val restlov = restaurantsResponse.value!!.data
                if (restlov != null) {
                    offlineCacheRestaurants(restlov)
                }
            } catch (e: Exception) {
                Log.d("MainViewModel", e.message.toString())
                restaurantsResponse.value = NetworkResult.Error(e.message)
            }
        } else {
            restaurantsResponse.value = NetworkResult.Error("No Internet Connection.")
        }
    }

    private fun offlineCacheRestaurants(restlov: Restaurant) {
        //val recipesEntity = RecipesEntity(foodRecipe)
        //insertRecipes(recipesEntity)
    }

    private fun handleRestaurantResponse(response: Response<Restaurant>): NetworkResult<Restaurant> {
        Log.d("DATA", response.body().toString())
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API Key Limited.")
            }
            response.code() == 404 -> {
                return NetworkResult.Error("Not found")
            }
            response.body()!!.isNullOrEmpty() -> {
                return NetworkResult.Error("Restaurants not found.")
            }
            response.isSuccessful -> {
                val restaurants = response.body()
                return NetworkResult.Success(restaurants)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}