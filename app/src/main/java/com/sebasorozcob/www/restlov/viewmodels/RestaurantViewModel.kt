package com.sebasorozcob.www.restlov.viewmodels

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.google.android.gms.location.*
import com.sebasorozcob.www.restlov.data.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    application: Application,
    private val dataStoreRepository: DataStoreRepository
) : AndroidViewModel(application) {

    lateinit var fusedLocationClient: FusedLocationProviderClient
    private var locationCallBack: LocationCallback? = null

    private var longitude = 0.0
    private var latitude = 0.0

    var networkStatus = false
    var backOnline = false // If backOnline is true is because it needs to connect to internet again

    private val readLatitudeLongitude = dataStoreRepository.readLongitudeLatitude
    val readBackOnline = dataStoreRepository.readBackOnline.asLiveData()


    private val _isLongitudeLatitudeReady = MutableLiveData<Boolean>()
    val isLongitudeLatitudeReady: LiveData<Boolean>
        get() = _isLongitudeLatitudeReady

    private fun saveLongitudeLatitude(longitude: Double, latitude: Double) =
        viewModelScope.launch(Dispatchers.IO) {
            if (longitude != 0.0 && latitude != 0.0) {
                dataStoreRepository.saveLongitudeLatitude(longitude, latitude)
            }
        }

    private fun saveBackOnline(backOnline: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveBackOnline(backOnline)
        }

    fun applyQueries(): ArrayList<String> {
        val query: ArrayList<String> = ArrayList()

        viewModelScope.launch {
            readLatitudeLongitude.collect {
                longitude = it.longitude
                latitude = it.latitude
            }
        }

        query.add(longitude.toString())
        query.add(latitude.toString())

        return query
    }

    fun showNetworkStatus() {
        if (!networkStatus) {
            Toast.makeText(getApplication(), "No Internet Connection!", Toast.LENGTH_LONG).show()
            saveBackOnline(true)
        } else if (networkStatus) {
            if (backOnline) {
                Toast.makeText(getApplication(), "We're back online.", Toast.LENGTH_LONG).show()
                saveBackOnline(false)
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun startLocationUpdates(activity: Activity) {
        val locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 3000
        locationRequest.fastestInterval = 1000
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        locationCallBack = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations) {
                    showPosition(fusedLocationClient)
                    fusedLocationClient.removeLocationUpdates(locationCallBack!!)
                }
            }
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallBack!!,
            Looper.getMainLooper()
        )
    }

    @SuppressLint("MissingPermission")
    fun showPosition(fusedLocationClient: FusedLocationProviderClient) {
        fusedLocationClient.lastLocation.addOnSuccessListener {
            // Got last known location. In some rare situations this can be null
            if (it != null) {
                val latitude = it.latitude
                val longitude = it.longitude

                _isLongitudeLatitudeReady.value = true

                Log.d("LONGITUDE_LATITUDE", "$latitude, $longitude")
                saveLongitudeLatitude(latitude, longitude)
            }
        }
    }
}