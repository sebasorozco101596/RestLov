package com.sebasorozcob.www.restlov.bindingadapters

import android.annotation.SuppressLint
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.sebasorozcob.www.restlov.R
import com.sebasorozcob.www.restlov.model.Location
import com.sebasorozcob.www.restlov.model.RestaurantItem
import com.sebasorozcob.www.restlov.ui.fragments.main.RestaurantsFragmentDirections

private const val TAG = "RestRowBinding"

class RestaurantsRowBinding {

    companion object {

        @BindingAdapter("onRecipeClickListener")
        @JvmStatic
        fun onRestaurantClickListener(
            restaurantRowLayout: ConstraintLayout,
            restaurant: RestaurantItem
        ) {
            Log.d(TAG, "CALLED")
            restaurantRowLayout.setOnClickListener {
                try {
                    val action =
                        RestaurantsFragmentDirections.actionRestaurantsFragmentToRestaurantActivity(
                            restaurant
                        )
                    restaurantRowLayout.findNavController().navigate(action)
                } catch (e: Exception) {
                    Log.d(TAG, e.toString())
                }
            }
        }

        @SuppressLint("SetTextI18n")
        @BindingAdapter("applyAddress")
        @JvmStatic
        fun applyAddress(textView: TextView, location: Location) {
            textView.text = "${location.address} , ${location.city} (${location.state})"
        }


        @BindingAdapter("loadImageFromUrl")
        @JvmStatic
        fun loadImageFromUrl(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
                error(R.drawable.ic_launcher_foreground)
            }
        }

        @BindingAdapter("applyMoneyLevelOne")
        @JvmStatic
        fun applyMoneyLevelColorOne(imageView: ImageView, moneyLevel: Int) {
            if (moneyLevel >= 1) {
                imageView.setColorFilter(
                    ContextCompat.getColor(
                        imageView.context,
                        R.color.yellow
                    )
                )
            }
        }

        @BindingAdapter("applyMoneyLevelTwo")
        @JvmStatic
        fun applyMoneyLevelColorTwo(imageView: ImageView, moneyLevel: Int) {
            if (moneyLevel >= 2) {
                imageView.setColorFilter(
                    ContextCompat.getColor(
                        imageView.context,
                        R.color.yellow
                    )
                )
            }
        }

        @BindingAdapter("applyMoneyLevelThree")
        @JvmStatic
        fun applyMoneyLevelColorThree(imageView: ImageView, moneyLevel: Int) {
            if (moneyLevel >= 3) {
                imageView.setColorFilter(
                    ContextCompat.getColor(
                        imageView.context,
                        R.color.yellow
                    )
                )
            }
        }

        @BindingAdapter("applyMoneyLevelFour")
        @JvmStatic
        fun applyMoneyLevelColorFour(imageView: ImageView, moneyLevel: Int) {
            if (moneyLevel >= 4) {
                imageView.setColorFilter(
                    ContextCompat.getColor(
                        imageView.context,
                        R.color.yellow
                    )
                )
            }
        }

        @BindingAdapter("applyMoneyLevelFive")
        @JvmStatic
        fun applyMoneyLevelColorFive(imageView: ImageView, moneyLevel: Int) {
            if (moneyLevel >= 5) {
                imageView.setColorFilter(
                    ContextCompat.getColor(
                        imageView.context,
                        R.color.yellow
                    )
                )
            }
        }
    }
}