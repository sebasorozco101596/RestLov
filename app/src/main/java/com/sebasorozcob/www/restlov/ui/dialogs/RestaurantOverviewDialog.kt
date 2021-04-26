package com.sebasorozcob.www.restlov.ui.dialogs

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.sebasorozcob.www.restlov.databinding.DialogRestaurantOverviewBinding
import com.sebasorozcob.www.restlov.model.RestaurantItem

class RestaurantOverviewDialog : DialogFragment() {

    private var _binding: DialogRestaurantOverviewBinding? = null
    val binding get() = _binding!!

    var restaurant: RestaurantItem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogRestaurantOverviewBinding.inflate(inflater, container, false)
        setUpView(binding)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setUpView(binding: DialogRestaurantOverviewBinding) {

        restaurant = arguments?.getParcelable(KEY_DIALOG)

        binding.restaurantNameTextView.text = restaurant?.name
        binding.restaurantPhoneTextView.text = restaurant?.phone
        val location =
            "${restaurant?.location?.address} ${restaurant?.location?.city} ${restaurant?.location?.state} ${restaurant?.location?.zipcode}"
        binding.restaurantAddressTextView.text = location //"43 Maple Ave Dover NJ 07801"
        binding.restaurantAddressTextView.movementMethod = LinkMovementMethod.getInstance()
        binding.restaurantDescriptionTextView.text = restaurant?.description
    }

    companion object {

        const val KEY_DIALOG = "RestaurantOverviewDialog"

        fun newInstance(
            restaurant: RestaurantItem
        ): RestaurantOverviewDialog {
            val args = Bundle()
            args.putParcelable(KEY_DIALOG, restaurant)
            val fragment = RestaurantOverviewDialog()
            fragment.arguments = args
            return fragment
        }
    }
}