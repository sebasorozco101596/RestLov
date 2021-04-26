package com.sebasorozcob.www.restlov.ui.fragments.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sebasorozcob.www.restlov.databinding.FragmentRestaurantsBinding
import com.sebasorozcob.www.restlov.model.Restaurant
import com.sebasorozcob.www.restlov.model.RestaurantItem
import com.sebasorozcob.www.restlov.ui.adapters.RestaurantsAdapter
import com.sebasorozcob.www.restlov.util.Constants.Companion.CODE_GPS
import com.sebasorozcob.www.restlov.util.NetworkListener
import com.sebasorozcob.www.restlov.util.NetworkResult
import com.sebasorozcob.www.restlov.util.observeOnce
import com.sebasorozcob.www.restlov.viewmodels.MainViewModel
import com.sebasorozcob.www.restlov.viewmodels.RestaurantViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*


private const val TAG = "RestaurantsFragment"

@AndroidEntryPoint
class RestaurantsFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val restaurantViewModel: RestaurantViewModel by activityViewModels()
    private var _binding: FragmentRestaurantsBinding? = null
    private val binding get() = _binding!!

    private lateinit var networkListener: NetworkListener

    private val mAdapter by lazy { RestaurantsAdapter() }

    var restaurantList: Restaurant? = null

    var restaurantSearch: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantsBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)
        setupRecyclerView()
        //requireGPSPermission()

        restaurantViewModel.readBackOnline.observe(viewLifecycleOwner, {
            restaurantViewModel.backOnline = it
        })


        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            lifecycleScope.launchWhenCreated {
                networkListener = NetworkListener()
                networkListener.checkNetworkAvailability(requireContext()).collect { status ->
                    Log.d("NetworkListener", "$status")
                    restaurantViewModel.networkStatus = status
                    restaurantViewModel.showNetworkStatus()
                    restaurantViewModel.startLocationUpdates(requireActivity())

                    readDatabase()
                }
            }
        } else {
            requireGPSPermission()
        }

        binding.getLocationImageButton.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                if (restaurantViewModel.networkStatus) {
                    restaurantViewModel.startLocationUpdates(requireActivity())
                    binding.restaurantSearchEditText.setText("")
                    restaurantSearch = null
                    readDatabase()
                }
            } else {
                requireGPSPermission()
            }
        }

        binding.restaurantSearchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(text: CharSequence, start: Int, count: Int, after: Int) {
                //Log.d("Editable", "$text: before")
            }

            override fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
                if (restaurantList != null && restaurantList!!.size != 0) {
                    Log.d("Editable", text.toString())
                    mAdapter.setData(searchRestaurants(restaurantList!!,text.toString()))
                }
            }

            override fun afterTextChanged(text: Editable) {
                // I don't need this method
            }
        })

        binding.searchButton.setOnClickListener {

            if (!binding.restaurantSearchEditText.text.isNullOrEmpty()) {
                restaurantSearch = binding.restaurantSearchEditText.text.toString()
                requestApiData()
            } else {
                Toast.makeText(requireContext(), "You need to type some letter", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return binding.root
    }

    private fun searchRestaurants(restaurantList: Restaurant, text: String): Restaurant {

        val newRestaurant= Restaurant()

        for (restaurant in restaurantList) {
            if (restaurant.name.trim().toLowerCase(Locale.ROOT).contains(text.trim())) {
                newRestaurant.add(restaurant)
            }
        }
        Log.d("Editable" , "${newRestaurant.size}")
        return newRestaurant
    }

    /**
     * The gps permission is obligatory for get the longitude and latitude for get
     * the close restaurants
     */
    private fun requireGPSPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            val permissions = arrayOf(String.toString())
            permissions[0] = Manifest.permission.ACCESS_FINE_LOCATION
            requestPermissions(permissions, CODE_GPS)
        } else {
            restaurantViewModel.startLocationUpdates(requireActivity())
        }
    }

    private fun readDatabase() {
        lifecycleScope.launch {
            mainViewModel.readRestaurants.observeOnce(viewLifecycleOwner, { database ->
                if (database.isNotEmpty()) {
                    Log.d(TAG, "readDatabase called!")
                    //mAdapter.setData(database[0].foodRecipe)
                    hideShimmerEffect()
                } else {
                    requestApiData()
                }
            })
        }
    }

    private fun showShimmerEffect() {
        binding.recyclerView.showShimmer()
    }

    private fun hideShimmerEffect() {
        binding.recyclerView.hideShimmer()
    }

    private fun requestApiData() {

        Log.d(TAG, "requestApiData called!")
        restaurantViewModel.isLongitudeLatitudeReady.observeOnce(viewLifecycleOwner, {

            val longitude = restaurantViewModel.applyQueries()[0].toDouble()
            val latitude = restaurantViewModel.applyQueries()[1].toDouble()
            if (restaurantSearch != null) {
                mainViewModel.getRestaurants(
                    longitude.toString(),
                    latitude.toString(),
                    restaurantSearch
                )
            } else {
                mainViewModel.getRestaurants(
                    longitude.toString(),
                    latitude.toString(),
                    restaurantSearch
                )
            }



            mainViewModel.restaurantsResponse.observe(viewLifecycleOwner, { response ->
                when (response) {
                    is NetworkResult.Success -> {
                        hideShimmerEffect()
                        response.data?.let {
                            mAdapter.setData(it)
                            restaurantList = it
                        }
                    }
                    is NetworkResult.Error -> {
                        hideShimmerEffect()
                        //loadDataFromCache() // That is for show to the user the last state of the db
                        Toast.makeText(
                            requireContext(),
                            response.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is NetworkResult.Loading -> {
                        showShimmerEffect()
                    }
                }
            })
        })

    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * For get the permissions of the application
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        lifecycleScope.launch {
            if (requestCode == CODE_GPS) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    restaurantViewModel.startLocationUpdates(requireActivity())

                    restaurantViewModel.isLongitudeLatitudeReady.observeOnce(viewLifecycleOwner, {
                        Log.d("LONGITUDE_OBSERVE", "STARTING")
                        readDatabase()
                    })

                } else {
                    Toast.makeText(requireContext(), "Permission not granted", Toast.LENGTH_SHORT)
                        .show()
                    hideShimmerEffect()
                }
            }
        }
    }
}