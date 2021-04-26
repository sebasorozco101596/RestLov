package com.sebasorozcob.www.restlov.ui.fragments.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sebasorozcob.www.restlov.databinding.FragmentProgressUserBinding
import com.sebasorozcob.www.restlov.model.Location
import com.sebasorozcob.www.restlov.model.RestaurantItem
import com.sebasorozcob.www.restlov.model.customer.Customer
import com.sebasorozcob.www.restlov.model.purchases.ChallengeProgress
import com.sebasorozcob.www.restlov.ui.adapters.ProgressChallengeAdapter
import com.sebasorozcob.www.restlov.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ChallengesProgressFragment : Fragment() {

    private var _binding: FragmentProgressUserBinding? = null
    private val binding get() = _binding!!

    private val mAdapter by lazy { ProgressChallengeAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgressUserBinding.inflate(inflater, container, false)

        val location = Location(
            "43 maple av",
            "Dover",
            "United States",
            "12awd",
            129129,
            129129,
            "New Jersey",
            0,
            "07801"
        )
        val restaurant1 = RestaurantItem(
            Constants.Currency.Dollar.toString(),
            "This restaurant have an excellent service to the customer, This restaurant have an excellent service to the customer, This restaurant have an excellent service to the customer, This restaurant have an excellent service to the customer ",
            null,
            "1129",
            "https://imagenesnoticias.canalrcn.com/ImgNoticias/gastronomia_colombiana_master_chef.jpg",
            location,
            null,
            "Sebas Delicias",
            "973 465 7855",
            3,
            0, null
        )

        val restaurant2 = RestaurantItem(
            Constants.Currency.Dollar.toString(),
            "This restaurant have an excellent service to the customer, This restaurant have an excellent service to the customer, This restaurant have an excellent service to the customer, This restaurant have an excellent service to the customer ",
            null,
            "1129",
            "https://www.restaurantecocorafusion.co/wp-content/uploads/2018/05/restaurante-cocora-cocina-colombiana-3-e1559790973593-800x480.jpg",
            location,
            null,
            "Clarena's 47",
            "973 465 7855",
            3,
            0, null
        )

        val restaurant3 = RestaurantItem(
            Constants.Currency.Dollar.toString(),
            "This restaurant have an excellent service to the customer, This restaurant have an excellent service to the customer, This restaurant have an excellent service to the customer, This restaurant have an excellent service to the customer ",
            null,
            "1129",
            "https://img-s-msn-com.akamaized.net/tenant/amp/entityid/BB15poFi.img?h=768&w=1080&m=6&q=60&o=t&l=f",
            location,
            null,
            "Marino's Coffee",
            "973 465 7855",
            4,
            0,
            null
        )

        val restaurant4 = RestaurantItem(
            Constants.Currency.Dollar.toString(),
            "This restaurant have an excellent service to the customer, This restaurant have an excellent service to the customer, This restaurant have an excellent service to the customer, This restaurant have an excellent service to the customer ",
            null,
            "1129",
            "http://www.lima2019.pe/sites/default/files/2019-07/PORTADA-001_2.jpg",
            location,
            null,
            "Las tres marias",
            "973 465 7855",
            4,
            0,
            null
        )

        val restaurant5 = RestaurantItem(
            Constants.Currency.Dollar.toString(),
            "This restaurant have an excellent service to the customer, This restaurant have an excellent service to the customer, This restaurant have an excellent service to the customer, This restaurant have an excellent service to the customer ",
            null,
            "1129",
            "https://blog.xcaret.com/es/wp-content/uploads/2017/04/comida-mexicana.jpg",
            location,
            null,
            "La bamba",
            "973 465 7855",
            4,
            0,
            null
        )


        val challenges = ArrayList<ChallengeProgress>()
        val customer = Customer(
            "sebasorozco1015",
            "12345",
            "Juan S",
            "Orozco Buitrago",
            location,
            "9734620735",
            null
        )

        /*

        val challenge1 = ChallengeProgress(
            restaurant1,
            customer,
            "For every $150 spent on food you will get 10% off on your next purchase",
            150.0,
            Constants.ChallengeType.PRICE,
            null,
            98
        )

        val beginDate = "01-02-2022"

        val challenge2 = ChallengeProgress(
            restaurant2,
            customer,
            "For every $200 spent on food you will get 20% off on your next purchase",
            200.0,
            Constants.ChallengeType.PRICE,
            beginDate,
            56
        )

        val challenge3 = ChallengeProgress(
            restaurant3,
            customer,
            "For every 12 purchases you will get 20% off on your next purchase",
            12.0,
            Constants.ChallengeType.SALES,
            beginDate,
            28
        )

        val challenge4 = ChallengeProgress(
            restaurant4,
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

         */

        setupRecyclerView()
        mAdapter.setData(challenges)

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //showShimmerEffect()
    }

}