package com.sebasorozcob.www.restlov.ui.fragments.restaurant

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sebasorozcob.www.restlov.databinding.FragmentChallengesRestaurantBinding
import com.sebasorozcob.www.restlov.model.Challenge
import com.sebasorozcob.www.restlov.model.RestaurantItem
import com.sebasorozcob.www.restlov.model.customer.Customer
import com.sebasorozcob.www.restlov.model.purchases.ChallengeProgress
import com.sebasorozcob.www.restlov.ui.adapters.RestaurantChallengesAdapter
import com.sebasorozcob.www.restlov.util.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*

class RestaurantChallengesFragment : Fragment() {

    private var _binding: FragmentChallengesRestaurantBinding? = null
    private val binding get() = _binding!!
    var challenges = emptyList<Challenge>()

    private val mAdapter by lazy { RestaurantChallengesAdapter() }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChallengesRestaurantBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: RestaurantItem? = args?.getParcelable(Constants.RESTAURANT_RESULT_KEY)

        if (myBundle?.challenges != null){
            challenges = myBundle.challenges
        }

        //fillChallenges(myBundle)

        setupRecyclerView()
        mAdapter.setData(challenges)

        return binding.root
    }

    /*
    private fun fillChallenges(myBundle: RestaurantItem?) {

        val customer = Customer(
            "sebasorozco1015", "12345",
            "Juan S", "Orozco Buitrago", myBundle!!.location, "9734620735", null
        )

        val challenge1 = ChallengeProgress(
            myBundle,
            customer,
            "For every $150 spent on food you will get 10% off on your next purchase",
            150.0,
            Constants.ChallengeType.PRICE,
            null,
            98
        )

        val beginDate = "01-02-2022"

        val challenge2 = ChallengeProgress(
            myBundle,
            customer,
            "For every $200 spent on food you will get 20% off on your next purchase",
            200.0,
            Constants.ChallengeType.PRICE,
            beginDate,
            56
        )

        val challenge3 = ChallengeProgress(
            myBundle,
            customer,
            "For every 12 purchases you will get 20% off on your next purchase",
            12.0,
            Constants.ChallengeType.SALES,
            beginDate,
            28
        )

        val challenge4 = ChallengeProgress(
            myBundle,
            customer,
            "For every 20 purchases you will get 30% off on your next purchase",
            20.0,
            Constants.ChallengeType.SALES,
            null,
            10
        )

        challenges.add(challenge1)
        challenges.add(challenge2)
        challenges.add(challenge3)
        challenges.add(challenge4)
    }

     */

    private fun setupRecyclerView() {
        binding.challengesRecyclerView.adapter = mAdapter
        binding.challengesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        //showShimmerEffect()
    }
}